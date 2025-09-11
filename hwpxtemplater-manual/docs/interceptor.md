### **4.1 개요**
---

```java
HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .interceptor( ... ) // 인터셉터 등록
                .parse("./hwpxtemplater.hwpx")

                ...
```

<br>

사용자는 위와 같이 hwpx 파일을 파싱하는 parse 메서드 호출 전 interceptor 메서드를 통해 특정한 인터셉터를 등록할 수 있습니다.

인터셉터를 이용하여 `hwpxTemplater` 내부에서 처리가 이뤄지는 특정 시점을 가로채어 `value` 값을 변경한다던지 특정한 가공 처리를 해낼 수 있습니다.

`hwpxTemplater`에서 제공하는 인터셉터로는 아래와 같은 것들이 있습니다. 

<br>

### **4.2 ValueInterceptor**
---

```java
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.interceptor.Interceptor;
import kr.mumberrymountain.hwpxtemplater.interceptor.ValueInterceptor;

import java.text.NumberFormat;
import java.util.*;

Interceptor valueInterceptor = new ValueInterceptor() {
    @Override
    public String intercept(String value, String field) {
        if (field.equals("Salary")) return NumberFormat.getNumberInstance(Locale.KOREA).format(Integer.parseInt(value)) + "만원"; // Salary의 Value 값으로 4000, 3000과 같이 숫자가 넘어올 때 `4000만원`, `3000만원`과 같이 포맷을 일괄 적용합니다.
        return value;
    }
};

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .interceptor(valueInterceptor)
                            .parse("./hwpxtemplater.hwpx")

                            ...
```

<br>

렌더링이 이뤄지기 전, `hwpxTemplater`에 파라미터로 기입한 데이터를 가로챌 수 있는 인터셉터입니다. 

이 인터셉터를 통해 특정 조건에 한정하여 `value` 값을 다른 `value`로 대체하거나, 혹은 예시와 같이 특정 `format`을 `value`에 일괄 적용할 수 있습니다. 

인터셉터 콜백의 파라미터로는 아래와 같은 것들이 있습니다.

<br>

| 이름                   | 타입         | 설명                                                   |
|----------------------|--------------|---------------------|
| value        | String     | 현재 렌더링이 이뤄지는 필드에 들어온 데이터 값 |
| field     | String   | 현재 렌더링이 이뤄지고 있는 템플릿 내 필드|

<br>

### **4.3 NullValueInterceptor**
---

```java
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.interceptor.Interceptor;
import kr.mumberrymountain.hwpxtemplater.interceptor.NullValueInterceptor;

import java.text.NumberFormat;
import java.util.*;

Interceptor nullValueInterceptor = new NullValueInterceptor() {
            @Override
            public String intercept(String value, String field) {
                if (field.equals("note")) return "없음"; // note 필드에 데이터가 null로 들어온 경우 "없음"으로 렌더링합니다.
                return value;
            }
        };

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
        .interceptor(nullValueInterceptor)
        .parse("./hwpxtemplater.hwpx")
        
        ...
```

<br>

렌더링이 이뤄지기 전, `hwpxTemplater`에 파라미터로 null로 기입된 데이터를 가로챌 수 있는 인터셉터입니다. 

null 값이 들어간 데이터만 다른 방식으로 렌더링하고 싶은 경우 유용합니다.

인터셉터 콜백의 파라미터로는 아래와 같은 것들이 있습니다.

<br>

| 파라미터                   | 타입         | 설명  |
|----------------------|--------------|---------------------|
| value        | String     | 현재 렌더링이 이뤄지는 필드에 들어온 데이터 값 |
| field     | String   | 현재 렌더링이 이뤄지고 있는 템플릿 내 필드|