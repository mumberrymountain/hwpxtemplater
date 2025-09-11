### **3.1 개요**
---

```java
HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .config(" ~~ ", " ~~ ") // 기본 설정 세팅
                .parse("./hwpxtemplater.hwpx")

                ...
```

<br>

사용자는 위와 같이 hwpx 파일을 파싱하는 parse 메서드 호출 전 config 메서드를 통해 `hwpxTemplater` 사용시 적용할 기본 옵션을 설정할 수 있습니다. 

`hwpxTemplater`의 기본 옵션으로는 아래와 같은 것들이 있습니다. 

<br>

### **3.2 delimPrefix**
---

<br>

```java
HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .config("delimPrefix", "[[") // 템플릿 문법의 기본 prefix를 [[로 대체
                .parse("./hwpxtemplater.hwpx")

                ...
```

<br>

`hwpxTemplater`의 기본 템플릿 문법으로 사용되는 `{{ 데이터 }}` 형태에서 `{{`를 다른 문자열로 대체하고 싶은 경우 사용하는 옵션입니다. 

렌더링하고 싶은 데이터에 `{`가 포함될 경우 유용합니다.

**제약사항: `delimPrefix`는 두 문자보다 길게 설정할 수 없습니다.**

<br>

### **3.3 delimPostfix**
---

<br>

```java
HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .config("delimSuffix", "]]") // 템플릿 문법의 기본 suffix를 [[로 대체
                .parse("./hwpxtemplater.hwpx")

                ...
```

<br>

`hwpxTemplater`의 기본 템플릿 문법으로 사용되는 `{{ 데이터 }}` 형태에서 `}}`를 다른 문자열로 대체하고 싶은 경우 사용하는 옵션입니다. 

렌더링하고 싶은 데이터에 `{`가 포함될 경우 유용합니다.

**제약사항: `delimSuffix`는 두 문자보다 길게 설정할 수 없습니다.**

<br>

### **3.3 charRoleSetter**
---

<br>

```java
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.model.CharRole;

CharRole charRole = new CharRole();
charRole.set(PlaceHolderType.CONDITION, '+'); // 조건문 태그를 판별할 때 사용되는 ? 문자를 다른 문자로 + 문자로 대체

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                                .config("charRoleSetter", charRole)

                                ...
```

<br>

조건문 태그를 판별할 때 사용되는 `?`, 반복문 태그를 판별할 때 사용되는 `#` 등 특정 태그가 어떤 태그인지 판별할 때 사용되는 역할 식별 문자를 디폴트 문자가 아닌 다른 문자로 설정하고 싶을 때 사용하는 옵션입니다.

`hwpxTemplater`에서 제공하는 `CharRole` 클래스를 이용해, Key 값으로 `PlaceHolderType`를, Value 값으로 문자를 넣어 인스턴스를 생성한 뒤 파라미터로 기입해 해당 옵션을 설정할 수 있습니다.

세팅할 수 있는 `PlaceHolderType`으로는 아래와 같은 것들이 있습니다.

<br>

| PlaceHolderType                   | 태그         | 디폴트 식별자                                                   |
|----------------------|--------------|---------------------|
| `PlaceHolderType.CONDITION`        | 조건문 태그 식별자     | `?`|
| `PlaceHolderType.LOOP`     | 반복문 태그 식별자   | `#`|
| `PlaceHolderType.CLOSURE` | 반복문 태그 식별자  | `/`|
| `PlaceHolderType.IMAGE_REPLACEMENT`         | 이미지 태그 식별자   | `$`|
| `PlaceHolderType.TABLE_REPLACEMENT`         | 테이블 태그 식별자  | `@`|

<br>

### **3.4 autoTrim**
---

<br>

```java
HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .config("autoTrim", true) // 데이터를 양쪽의 공백을 자동으로 trim처리
                .parse("./hwpxtemplater.hwpx")
                .render(new HashMap<String, Object>() {{
                    put("data", "    데이터     "); // 양쪽의 공백을 제거하여 렌더링함
                    }}
                );

                ...
```

hwpx 템플릿 파일에 데이터를 렌더링할 때, 데이터 양옆의 공백을 자동으로 제거하여 렌더링하고 싶은 경우 사용하는 옵션입니다.

<br>
