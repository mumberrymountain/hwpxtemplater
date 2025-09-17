# HWPXTemplater

HWPXTemplater는 HWPX 템플릿 파일을 기반으로 데이터를 주입하여 HWPX 파일을 생성하는 Java 라이브러리입니다.

&nbsp;
## 🚀 주요 기능

- **템플릿 기반 문서 생성**: HWPX 템플릿 파일에 동적 데이터 삽입
- **다양한 태그 지원**: 기본 태그, 조건문, 반복문, 이미지, 테이블 태그
- **유연한 설정**: 템플릿 문법 커스터마이징 및 자동 트림 옵션
- **인터셉터 시스템**: 데이터 렌더링 전 가로채기 및 가공
- **풍부한 데이터 모델**: Text, Image, Table 등 특수 데이터 모델 제공

&nbsp;
## 📋 요구사양

- HWPXTemplater를 사용하시려면 반드시 Java8, 혹은 높은 버전의 자바를 사용하셔야 됩니다.

&nbsp;
## 📦 설치

빌드 도구로 maven을 사용하시는 경우에는 아래의 dependency를 pom.xml 파일에 추가해주세요.

### Maven
```xml
<dependency>
  <groupId>com.mumberrymountain</groupId>
  <artifactId>hwpxtemplater</artifactId>
</dependency>
```

gradle을 사용하시는 경우에는 아래의 implementation을 build.gradle 파일에 추가해주세요.

### Gradle
```gradle
implementation 'com.mumberrymountain:hwpxtemplater'
```

&nbsp;
## 🏷️ 템플릿 태그

&nbsp;
### 기본 태그
```
{{field_name}}
```
**용도**: 기본적인 텍스트 데이터를 HWPX 파일에 렌더링하는 가장 기본적인 태그입니다.

**사용법**: 
- 템플릿 파일에 `{{name}}` 형태로 작성합니다.
- Java 코드에서 해당 키에 문자열 값을 매핑합니다.
- 렌더링 시 태그가 실제 데이터로 치환됩니다.

**예시**:
```java
// 템플릿: {{name}}님 안녕하세요!
import javax.servlet.http.HttpServletResponse;
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import java.util.*;

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                    .parse("./hwpxtemplater.hwpx")
                    .render(new HashMap<String, Object>() {{
                        put("name", "홍길동");
                    }})
                    .write(response.getOutputStream());

// 결과: 홍길동님 안녕하세요!
```

&nbsp;
### 조건문 태그
```
{{?condition}}
  조건이 참일 때 렌더링될 내용
{{/condition}}
```
**용도**: 조건에 따라 태그 내부의 데이터를 렌더링할지 여부를 결정하는 태그입니다.

**동작 원리**:
- 조건값이 `true`이면 태그 내부 내용이 렌더링됩니다
- 조건값이 `false`이면 태그 내부 내용이 완전히 제외됩니다
- 태그 내부에 다른 기본 태그들도 포함할 수 있습니다

**예시**:
```java
// 템플릿: 
// 상태: {{status}}
// {{?hasTrackingNumber}}
// 운송장 번호: {{trackingNumber}}
// {{/hasTrackingNumber}}

import javax.servlet.http.HttpServletResponse;
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import java.util.*;

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .parse("./hwpxtemplater.hwpx")
                            .render(new HashMap<String, Object>() {{
                                put("status", "배송중");
                                put("hasTrackingNumber", true);
                                put("trackingNumber", "1234-5678-9012");
                            }})
                            .write(response.getOutputStream());

// 결과: 
// 상태: 배송중
// 운송장 번호: 1234-5678-9012
```

&nbsp;
### 반복문 태그
```
{{#loop}}
  {{item_field}}
{{/loop}}
```
**용도**: 배열이나 리스트 형태의 데이터를 반복하여 동일한 패턴으로 렌더링하는 태그입니다.

**동작 원리**:
- List나 Array 형태의 데이터를 받습니다
- 각 요소마다 태그 내부 내용을 반복 렌더링합니다
- 내부의 기본 태그들은 각 배열 요소의 필드값으로 치환됩니다

**예시**:
```java
import javax.servlet.http.HttpServletResponse;
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import java.util.*;

// 템플릿:
// {{#products}}
// - {{name}}: {{price}}원
// {{/products}}

ArrayList<HashMap<String, Object>> products = new ArrayList<>();
products.add(new HashMap<String, Object>() {{
    put("name", "사과");
    put("price", 1500);
}});
products.add(new HashMap<String, Object>() {{
    put("name", "바나나");
    put("price", 700);
}});

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
        .parse("./hwpxtemplater.hwpx")
        .render(new HashMap<String, Object>() {{
            put("products", products);
        }})
        .write(response.getOutputStream());

// 결과:
// - 사과: 1500원
// - 바나나: 700원
```

&nbsp;
### 이미지 태그
```
{{$image_field}}
```
**용도**: HWPX 템플릿 파일에 이미지를 삽입하기 위한 전용 태그입니다.

**동작 원리**:
- 이미지 파일 경로를 문자열로 받습니다
- 해당 경로의 이미지를 HWPX 파일에 삽입합니다
- Image 데이터 모델을 사용하면 크기 조절도 가능합니다

**지원 형식**: PNG, JPG, GIF 등 일반적인 이미지 형식

**예시**:
```java
import javax.servlet.http.HttpServletResponse;
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import java.util.*;

// 템플릿: {{$flag}}

// 방법 1: 경로 문자열 사용
HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                    .parse("./hwpxtemplater.hwpx")
                    .render(new HashMap<String, Object>() {{
                        put("flag", "images/korea.png");
                    }})
                    .write(response.getOutputStream());

// 방법 2: Image 모델 사용 (크기 지정 가능)
HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                    .parse("./hwpxtemplater.hwpx")
                    .render(new HashMap<String, Object>() {{
                        put("flag", new Image("images/korea.png").width(50).height(50));
                    }})
                    .write(response.getOutputStream());
```

&nbsp;
### 테이블 태그
```
{{@table_field}}
```
**용도**: HWPX 파일에 표 형태의 데이터를 삽입하기 위한 전용 태그입니다.

**동작 원리**:
- 오직 `Table` 데이터 모델만을 데이터로 받습니다
- 열(Column) 정의와 행(Row) 데이터를 기반으로 표를 생성합니다
- 각 열의 너비, 데이터 타입 등을 세밀하게 제어할 수 있습니다

**예시**:
```java
import javax.servlet.http.HttpServletResponse;
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.model.table.Col;
import kr.mumberrymountain.hwpxtemplater.model.table.Table;

// 템플릿: {{@scoreTable}}

Table table = Table.builder()
    .cols(Arrays.asList(
        new Col("이름").width(100),
        new Col("점수").width(80),
        new Col("등급").width(60)
    ))
    .row(new HashMap<String, Object>() {{
        put("이름", "홍길동");
        put("점수", 95);
        put("등급", "A");
    }})
    .row(new HashMap<String, Object>() {{
        put("이름", "김철수");
        put("점수", 87);
        put("등급", "B");
    }})
    .create();

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                    .parse("./hwpxtemplater.hwpx")
                    .render(new HashMap<String, Object>() {{
                        put("scoreTable", table);
                    }})
                    .write(response.getOutputStream());
```

&nbsp;
## ⚙️ 기본 설정

&nbsp;
### ConfigOption.DELIM_PREFIX
```java
HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
    .config(ConfigOption.DELIM_PREFIX, "[[")
    .parse("./template.hwpx")
    // ...
```

기본 템플릿 문법으로 사용되는 {{ 데이터 }} 형태에서 {{를 다른 문자열로 대체하고 싶은 경우 사용하는 옵션입니다.

**제약사항: ConfigOption.DELIM_PREFIX는 두 문자보다 길게 설정할 수 없습니다.**

&nbsp;
### ConfigOption.DELIM_SUFFIX
```java
HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
    .config(ConfigOption.DELIM_PREFIX, "[[")
    .parse("./template.hwpx")
    // ...
```

기본 템플릿 문법으로 사용되는 {{ 데이터 }} 형태에서 }}를 다른 문자열로 대체하고 싶은 경우 사용하는 옵션입니다.

**제약사항: ConfigOption.DELIM_SUFFIX는 두 문자보다 길게 설정할 수 없습니다.**

&nbsp;
### ConfigOption.CHAR_ROLE_SETTER
```java
CharRole charRole = new CharRole();
charRole.set(PlaceHolderType.CONDITION, '+');

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
    .config(ConfigOption.CHAR_ROLE_SETTER, charRole)
    .parse("./template.hwpx")
    // ...
```

조건문 태그를 판별할 때 사용되는 ?, 반복문 태그를 판별할 때 사용되는 # 등 특정 태그가 어떤 태그인지 판별할 때 사용되는 역할 식별 문자를 디폴트 문자가 아닌 다른 문자로 설정하고 싶을 때 사용하는 옵션입니다.

CharRole 모델을 이용해 Key 값으로 PlaceHolderType를, Value 값으로 문자를 넣어 인스턴스를 생성한 뒤 파라미터로 기입해 해당 옵션을 설정할 수 있습니다.

세팅할 수 있는 PlaceHolderType으로는 아래와 같은 것들이 있습니다.

| PlaceHolderType                   | 태그         | 디폴트 식별자|
|----------------------|--------------|---------------------|
| PlaceHolderType.CONDITION        | 조건문 태그 식별자     |?|
| PlaceHolderType.LOOP     | 반복문 태그 식별자   |#|
| PlaceHolderType.CLOSURE | 반복문 태그 식별자  | /|
| PlaceHolderType.IMAGE_REPLACEMENT         | 이미지 태그 식별자   | $|
| PlaceHolderType.TABLE_REPLACEMENT         | 테이블 태그 식별자  | @|

&nbsp;
### ConfigOption.AUTO_TRIM
```java
HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .config(ConfigOption.AUTO_TRIM, true) // 데이터를 양쪽의 공백을 자동으로 trim처리
                .parse("./hwpxtemplater.hwpx")
                .render(new HashMap<String, Object>() {{
                    put("data", "    데이터     "); // 양쪽의 공백을 제거하여 렌더링함
                    }}
                );
```

템플릿 파일에 데이터를 렌더링할 때 데이터 양옆의 공백을 자동으로 제거하여 렌더링하고 싶은 경우 사용하는 옵션입니다.

&nbsp;
## 🎯 인터셉터

&nbsp;
### ValueInterceptor
데이터 렌더링 전 값을 가로채어 가공할 수 있습니다.

```java
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.interceptor.Interceptor;
import kr.mumberrymountain.hwpxtemplater.interceptor.ValueInterceptor;

import java.text.NumberFormat;
import java.util.*;

Interceptor valueInterceptor = new ValueInterceptor() {
    @Override
    public String intercept(String value, String field) {
        if (field.equals("Salary")) {
            return NumberFormat.getNumberInstance(Locale.KOREA).format(Integer.parseInt(value)) + "만원";
        }
        return value;
    }
};

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
    .interceptor(valueInterceptor)
    .parse("./template.hwpx")
    // ...
```

콜백의 파라미터는 아래와 같습니다. 

| 이름                   | 타입         | 설명 |
|----------------------|--------------|---------------------|
| value        | String     | 현재 렌더링이 이뤄지는 필드에 들어온 데이터 값 |
| field     | String   | 현재 렌더링이 이뤄지고 있는 템플릿 내 필드|

&nbsp;
### NullValueInterceptor
null 값에 대해서만 특별한 처리를 할 수 있습니다.

```java
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.interceptor.Interceptor;
import kr.mumberrymountain.hwpxtemplater.interceptor.NullValueInterceptor;

import java.text.NumberFormat;
import java.util.*;

Interceptor nullValueInterceptor = new NullValueInterceptor() {
    @Override
    public String intercept(String value, String field) {
        if (field.equals("note")) return "없음";
        return value;
    }
};

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
        .interceptor(nullValueInterceptor)
        .parse("./hwpxtemplater.hwpx")
        
        ...

```

콜백의 파라미터는 아래와 같습니다. 

| 파라미터                   | 타입         | 설명  |
|----------------------|--------------|---------------------|
| value        | String     | 현재 렌더링이 이뤄지는 필드에 들어온 데이터 값 |
| field     | String   | 현재 렌더링이 이뤄지고 있는 템플릿 내 필드|

&nbsp;
## 📊 데이터 모델

&nbsp;
### Text 모델
텍스트에 스타일을 적용할 수 있습니다.

```java
new Text("데이터")
    .fontColor("#9C3B00")
    .backgroundColor("#FFEF99")
    .fontFamily("Noto Sans KR")
    .fontSize(20)
```

&nbsp;
### Image 모델
이미지 크기를 지정할 수 있습니다.

```java
new Image("./korea.png")
    .width(50)
    .height(50)
```

&nbsp;
### Table 모델
테이블을 생성할 수 있습니다.

```java
Table table = Table.builder()
    .cols(Arrays.asList(
        new Col("번호").width(60),
        new Col("이름").width(150),
        new Col("점수").width(150)
    ))
    .row(new HashMap<String, Object>() {{
        put("번호", 1);
        put("이름", "홍길동");
        put("점수", 85);
    }})
    .create();
```

&nbsp;
## 📚 더 많은 정보

자세한 사용법과 API 문서는 [공식 문서](https://mumberrymountain.github.io/hwpxtemplater/)를 참조해주세요.

&nbsp;
## 🤝 기여하기

버그 리포트나 기능 제안은 [이슈 트래커](https://github.com/mumberrymountain/hwpxtemplater/issues)를 통해 제출해주세요.
