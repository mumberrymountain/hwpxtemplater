### **5.1 개요**
---

`hwpxTemplater`에서는 사용자가 특정한 상황에서 `String`, `Integer` 등 자바가 제공하는 일반적인 타입을 대신하여 파라미터로 기입할 수 있는 데이터 모델을 제공합니다.

<br>

### **5.2 Text**
---

```java
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.model.Text;

import java.util.*;

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                    .parse("./hwpxtemplater.hwpx")
                    .render(new HashMap<String, Object>() {{
                        put("Data", new Text("데이터").fontColor("#9C3B00") // 폰트 색상으로 #9C3B00 적용
                                                    .backgroundColor("#FFEF99") // 배경 색상으로 #FFEF99 적용
                                                    .fontFamily("Noto Sans KR") // 폰트로 Noto Sans KR 적용
                                                    .fontSize(20) // 글자 크기로 20px 적용
                            );
                    }});
```

`Text` 데이터 모델은 기본 태그에 데이터를 넣을 때 일반적인 자바 데이터 타입을 대신하여 넣을 수 있는 데이터 모델입니다. <br>

`Text` 클래스에서 제공하는 메서드들을 사용하여 렌더링하려는 데이터의 스타일을 커스텀하게 설정할 수 있습니다.

`Text` 클래스에서 제공하는 메서드로는 아래와 같은 것들이 있습니다.

<br>

|메서드                 |설명                   |
|----------------------|----------------------|
| fontColor           | 렌더링하려는 데이터의 폰트 색상을 지정합니다. |
| backgroundColor     | 렌더링하려는 데이터의 배경 색상을 지정합니다. |
| fontFamily          | 렌더링하려는 데이터의 폰트 스타일을 지정합니다. |
| fontSize            | 렌더링하려는 데이터의 폰트 사이즈를 지정합니다. |

<br>

### **5.3 Image**
---

```java
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.model.Image;

import java.util.*;

HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse("./hwpxtemplater.hwpx")
                .render(new HashMap<String, Object>() {{
                    put("Korea", new Image("./korea.png").width(50).height(50)); // korea.png 이미지를 50px 너비, 50px 높이로 렌더링합니다.
                }});
```

<br>

`Image` 데이터 모델은 이미지 태그에 렌더링할 데이터를 넣을 때, 이미지 경로를 대신하여 넣을 수 있는 데이터 모델입니다. <br>

`Image` 데이터 모델을 이용하여 이미지를 렌더링할 때 너비와 높이를 별도로 지정하여 렌더링할 수 있습니다. <br>

`Image` 클래스에서 제공하는 메서드로는 아래와 같은 것들이 있습니다.

<br>

|메서드                 |설명                  |
|----------------------|----------------------|
| width                | 렌더링하려는 이미지의 너비를 지정합니다. |
| height               | 렌더링하려는 이미지의 높이를 지정합니다. |

<br>

### **5.4 Table**
---

```java
import javax.servlet.http.HttpServletResponse;
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.model.table.Col;
import kr.mumberrymountain.hwpxtemplater.model.table.Table;

Table table = Table.builder()
                    .cols(
                            Arrays.asList(
                                    new Col("번호").width(60),
                                    new Col("이름").width(150),
                                    new Col("점수").width(150),
                                    new Col("비고").width(150)
                            )
                    )
                    .row(new HashMap<String, Object>() {{
                        put("번호", "번호");
                        put("이름", "이름");
                        put("점수", "점수");
                        put("비고", "비고");
                    }})
                    .row(new HashMap<String, Object>() {{
                        put("번호", 1);
                        put("이름", "홍길동");
                        put("점수", 85);
                        put("비고", null);
                    }})
                    .row(new HashMap<String, Object>() {{
                        put("번호", 2);
                        put("이름", "김철수");
                        put("점수", 	90);
                        put("비고", "우수 성적");
                    }})
                    .row(new HashMap<String, Object>() {{
                        put("번호", 3);
                        put("이름", "이영희");
                        put("점수", 78);
                    }})
                    .create();

            HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                    .parse("./hwpxtemplater.hwpx")
                    .render(new HashMap<String, Object>() {{
                        put("table", table);
                    }})
                    .write(response.getOutputStream());
```

<br>

`Table` 데이터 모델은 테이블 태그에 테이블을 렌더링할 때 기입하는 특수한 데이터 모델입니다. <br>

테이블 태그에는 오직 `Table` 데이터 모델만을 데이터로 기입할 수 있습니다.