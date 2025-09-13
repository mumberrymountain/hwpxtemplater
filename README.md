# HWPXTemplater

HWPXTemplater는 HWPX 템플릿 파일을 기반으로 데이터를 주입하여 HWPX 파일을 생성하는 Java 라이브러리입니다.

## 🚀 주요 기능

- **템플릿 기반 문서 생성**: HWPX 템플릿 파일에 동적 데이터 삽입
- **다양한 태그 지원**: 기본 태그, 조건문, 반복문, 이미지, 테이블 태그
- **유연한 설정**: 템플릿 문법 커스터마이징 및 자동 트림 옵션
- **인터셉터 시스템**: 데이터 렌더링 전 가로채기 및 가공
- **풍부한 데이터 모델**: Text, Image, Table 등 특수 데이터 모델 제공

## 📋 요구사양

- Java 8 이상

## 📦 설치

### Maven
```xml
<dependency>
  <groupId>com.mumberrymountain</groupId>
  <artifactId>hwpxtemplater</artifactId>
</dependency>
```

### Gradle
```gradle
implementation 'com.mumberrymountain:hwpxtemplater'
```

## 🏃‍♂️ 빠른 시작

1. **템플릿 파일 생성**: `hwpxtemplater.hwpx` 파일을 만들고 `{{hwpxTemplater}}` 필드를 추가합니다.

2. **Java 코드 작성**:
```java
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import java.util.HashMap;

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
    .parse("./hwpxtemplater.hwpx")
    .render(new HashMap<String, Object>() {{
        put("hwpxTemplater", "hwpxTemplater은 hwpx 템플릿 파일을 기반으로 데이터를 주입하여 hwpx 파일을 생성하는 라이브러리입니다.");
    }})
    .write(response.getOutputStream());
```

## 🏷️ 템플릿 태그

### 기본 태그
```
{{field_name}}
```
**용도**: 기본적인 텍스트 데이터를 HWPX 파일에 렌더링하는 가장 기본적인 태그입니다.

**사용법**: 
- 템플릿 파일에 `{{hangul}}` 형태로 작성
- Java 코드에서 해당 키에 문자열 값을 매핑
- 렌더링 시 태그가 실제 데이터로 치환됩니다

**예시**:
```java
// 템플릿: {{name}}님 안녕하세요!
put("name", "홍길동");
// 결과: 홍길동님 안녕하세요!
```

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

put("status", "배송중");
put("hasTrackingNumber", true);
put("trackingNumber", "1234-5678-9012");

// 결과: 
// 상태: 배송중
// 운송장 번호: 1234-5678-9012
```

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
put("products", products);

// 결과:
// - 사과: 1500원
// - 바나나: 700원
```

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
// 템플릿: {{$countryFlag}}

// 방법 1: 경로 문자열 사용
put("countryFlag", "images/korea.png");

// 방법 2: Image 모델 사용 (크기 지정 가능)
put("countryFlag", new Image("images/korea.png").width(50).height(50));
```

### 테이블 태그
```
{{@table_field}}
```
**용도**: HWPX 파일에 표 형태의 데이터를 삽입하기 위한 전용 태그입니다.

**동작 원리**:
- 오직 `Table` 데이터 모델만을 데이터로 받습니다
- 열(Column) 정의와 행(Row) 데이터를 기반으로 표를 생성합니다
- 각 열의 너비, 데이터 타입 등을 세밀하게 제어할 수 있습니다

**특징**:
- 동적인 행 개수 지원
- 열별 너비 설정 가능
- null 값 자동 처리

**예시**:
```java
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

put("scoreTable", table);
```

## ⚙️ 기본 설정

### 구분자 변경
```java
HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
    .config(ConfigOption.DELIM_PREFIX, "[[")
    .config(ConfigOption.DELIM_SUFFIX, "]]")
    .parse("./template.hwpx")
    // ...
```

### 자동 트림
```java
HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
    .config(ConfigOption.AUTO_TRIM, true)
    .parse("./template.hwpx")
    // ...
```

### 역할 식별자 변경
```java
CharRole charRole = new CharRole();
charRole.set(PlaceHolderType.CONDITION, '+');

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
    .config(ConfigOption.CHAR_ROLE_SETTER, charRole)
    .parse("./template.hwpx")
    // ...
```

## 🎯 인터셉터

### ValueInterceptor
데이터 렌더링 전 값을 가로채어 가공할 수 있습니다.

```java
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

### NullValueInterceptor
null 값에 대해서만 특별한 처리를 할 수 있습니다.

```java
Interceptor nullValueInterceptor = new NullValueInterceptor() {
    @Override
    public String intercept(String value, String field) {
        if (field.equals("note")) return "없음";
        return value;
    }
};
```

## 📊 데이터 모델

### Text 모델
텍스트에 스타일을 적용할 수 있습니다.

```java
new Text("데이터")
    .fontColor("#9C3B00")
    .backgroundColor("#FFEF99")
    .fontFamily("Noto Sans KR")
    .fontSize(20)
```

### Image 모델
이미지 크기를 지정할 수 있습니다.

```java
new Image("./korea.png")
    .width(50)
    .height(50)
```

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

## 📚 더 많은 정보

자세한 사용법과 API 문서는 [공식 문서](링크)를 참조해주세요.

## 🤝 기여하기

버그 리포트나 기능 제안은 [이슈 트래커](https://github.com/mumberrymountain/hwpxtemplater/issues)를 통해 제출해주세요.