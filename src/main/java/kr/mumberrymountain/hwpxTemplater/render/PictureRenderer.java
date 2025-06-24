package kr.mumberrymountain.hwpxTemplater.render;

import kr.dogfoot.hwpxlib.object.content.header_xml.enumtype.ImageEffect;
import kr.dogfoot.hwpxlib.object.content.section_xml.enumtype.*;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Picture;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.picture.ImageRect;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.shapecomponent.RenderingInfo;
import kr.mumberrymountain.hwpxTemplater.render.image.ImageInfo;

import java.util.UUID;

public class PictureRenderer {

    private final ImageInfo imageInfo;
    private final Picture picture;
    public PictureRenderer(ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
        this.picture = new Picture();
    }

    /*
        id: 객체를 식별하기 위한 아이디
        zOrder: z-order (여러 객체가 동일한 페이지에 있을 때, 어떤 객체가 앞에 보이고 어떤 객체가 뒤에 보일지 결정하는 요소로 추정)
        numberingType: 객체가 속하는 번호 범위
        textWrap: 객체 주위를 텍스트가 어떻게 흘러갈지 정하는 옵션. 하위 요소 pos의 속성 중 "treatAsChar"이 "false"일 때만 사용.
        textFlow: 객체의 좌우 어느 쪽에 글을 배치할지 정하는 옵션. textWrap 속성이 "SQUARE" 또는 "TIGHT" 또는 "THROUGH"일 때만 사용.
        lock: 객체 선택 가능 여부
        dropcapStyle: 첫 글자 장식 스타일 - None: 없음 / DoubleLine: 2줄 / TripleLine: 3줄 / Margin: 여백
        reverse: 그림 색상 반전 여부
    */
    private void setPic(){
        picture.idAnd(RendererUtil.getRandomId()).zOrderAnd(1).numberingTypeAnd(NumberingType.PICTURE).textWrapAnd(TextWrapMethod.SQUARE)
                        .textFlowAnd(TextFlowSide.BOTH_SIDES).lockAnd(false).dropcapstyleAnd(DropCapStyle.None)
                        .groupLevelAnd((short) 0).reverseAnd(false);
    }

    /*
        offset: 그룹 객체 내에서 개별 객체들의 그룹 내 상대 위치 정보를 가진 요소
         - x: 객체가 속한 그룹 내에서의 x offset
         - y: 객체가 속한 그룹 내에서의 y offset
    */
    private void setOffset(){
        picture.createOffset();
        picture.offset().xAnd((long) 0).yAnd((long) 0);
    }

    /*
        orgSz: 객체 생성시 최초 크기 정보를 가진 요소
         - width: 개체 생성시 최초 너비. 단위는 HWPUNIT
         - height: 개체 생성시 최초 높이. 단위는 HWPUNIT
    */
    private void setOrgSz(){
        picture.createOrgSz();
        picture.orgSz().widthAnd((long) imageInfo.width()).height((long) imageInfo.height());
    }

    /*
        curSz: 객체의 현재 크기 정보를 가진 요소
         - width: 개체의 현재 너비. 단위는 HWPUNIT
         - height: 개체의 현재 높이. 단위는 HWPUNIT
    */
    private void setCurSz(){
        picture.createCurSz();
        picture.curSz().widthAnd((long) 0).height((long) 0);
    }

    /*
        flip: 객체의 반전 여부 정보를 가진 요소
         - horizontal: 좌우로 뒤집어진 상태인지 여부
         - vertical: 상하로 뒤집어진 상태인지 여부
    */
    private void setFilp(){
        picture.createFlip();
        picture.flip().horizontalAnd(false).vertical(false);
    }

    /*
        rotationInfo: 객체의 회전 정보를 가진 요소
         - angle: 회전각
         - centerX: 회전 중심의 x 좌표
         - centerY: 회전 중심의 y 좌표
    */
    private void setRotationInfo(){
        picture.createRotationInfo();
        picture.rotationInfo().angleAnd((short) 0).centerXAnd((long)0).centerYAnd((long)0).rotateimage(true);
    }

    /*
        renderingInfo: 객체 렌더링시 필요한 변환 행렬, 확대/축소 행렬, 회전 행렬을 가진 요소
         - transMatrix: Translation Matrix
         - scaMatrix: Scaling Matrix
         - rotMatrix: Rotation Matrix
    */
    private void setRenderingInfo(){
        picture.createRenderingInfo();
        RenderingInfo ri =  picture.renderingInfo();
        ri.addNewTransMatrix().e1And((float) 1).e2And((float) 0).e3And((float) 0).e4And((float) 0).e5And((float) 1).e6And((float) 0);
        ri.addNewRotMatrix().e1And((float) 1).e2And((float) 0).e3And((float) 0).e4And((float) 0).e5And((float) 1).e6And((float) 0);
        ri.addNewScaMatrix().e1And((float) 1).e2And((float) 0).e3And((float) 0).e4And((float) 0).e5And((float) 1).e6And((float) 0);
    }

    /*
        img: 그림 정보를 표현하기 위한 요소. 그림 데이터에 대한 참조 아이디 및 그림에 적용될 몇몇 효과들에 관한 정보를 포함함.
         - binaryItemIDRef: BinDataItem 요소의 아이디 참조값. 그림의 바이너리 데이터에 대한 연결 정보.
         - bright: 그림의 밝기
         - contrast: 그림의 명암
         - effect: 그림의 추가 효과 - REAL_PIC: 원래 그림대로 / GRAY_SCALE: 그레이 스케일로 / BLACK_WHITE: 흑백으로
         - alpha: 투명도
    */
    private void setImage(){
        picture.createImg();
        picture.img().binaryItemIDRefAnd(imageInfo.id()).brightAnd(0).contrastAnd(0).effectAnd(ImageEffect.REAL_PIC).alphaAnd((float) 0);
    }

    /*
        imgRect: 그림의 좌표 정보를 가진 요소
         - pt0: 첫 번째 좌표. x, y로 구성된 2축 좌표계 사용.
         - pt1: 두 번째 좌표. x, y로 구성된 2축 좌표계 사용.
         - pt2: 세 번째 좌표. x, y로 구성된 2축 좌표계 사용.
         - pt3: 네 번째 좌표. x, y로 구성된 2축 좌표계 사용.
    */
    private void setImageRect(){
        picture.createImgRect();
        ImageRect imageRect = picture.imgRect();
        imageRect.createPt0();
        imageRect.pt0().xAnd((long) 0).yAnd((long) 0);
        imageRect.createPt1();
        imageRect.pt1().xAnd((long) imageInfo.width()).yAnd((long) 0);
        imageRect.createPt2();
        imageRect.pt2().xAnd((long) imageInfo.width()).yAnd((long) imageInfo.height());
        imageRect.createPt3();
        imageRect.pt3().xAnd((long) 0).yAnd((long) imageInfo.height());
    }

    /*
        imgRect: 원본 그림을 기준으로 자를 영역 정보를 가진 요소. 자르기 정보가 설정되면, 그림은 논리적으로 원본 그림에서 해당 영역만큼 잘리게 되고, 화면에서는 남은 영역만 표시됨.
         - left: 왼쪽에서 이미지를 자른 크기
         - right: 오른쪽에서 이미지를 자른 크기
         - top: 위쪽에서 이미지를 자른 크기
         - bottom: 아래쪽에서 이미지를 자른 크기
    */
    private void setImageClip(){
        picture.createImgClip();
        picture.imgClip().leftAnd((long)0).rightAnd((long) imageInfo.width()).topAnd((long)0).bottomAnd((long)imageInfo.height());
    }

    /*
        inMargin: 안쪽 여백 정보
         - left: 왼쪽 여백. 단위는 HWPUNIT.
         - right: 오른쪽 여백. 단위는 HWPUNIT.
         - top: 위쪽 여백. 단위는 HWPUNIT.
         - bottom: 아래쪽 여백. 단위는 HWPUNIT.
    */
    private void setInMargin(){
        picture.createInMargin();
        picture.inMargin().leftAnd((long)0).rightAnd((long)0).topAnd((long)0).bottomAnd((long)0);
    }

    /*
        imgDim: 원본 그림의 크기 정보를 가진 요소
         - dimwidth: 원본 너비
         - dimheight: 원본 높이
    */
    private void setImageDim(){
        picture.createImgDim();
        picture.imgDim().dimwidthAnd((long) imageInfo.width()).dimheight((long) imageInfo.height());
    }

    /*
        effects: 그림에 적용될 효과 정보를 가진 요소
    */
    private void setEffects(){
        picture.createEffects();
    }

    /*
        sz: 객체들의 크기 정보를 가진 요소
         - width: 객체 너비
         - widthRelTo: 객체 너비 기준
         - height: 객체 높이
         - heightRelTo: 객체 높이 기준
    */
    private void setSz(){
        picture.createSZ();
        picture.sz().widthAnd((long) imageInfo.width()).widthRelToAnd(WidthRelTo.ABSOLUTE).heightAnd((long) imageInfo.height())
                .heightRelToAnd(HeightRelTo.ABSOLUTE).protectAnd(false);
    }

    /*
        pos: 객체들의 위치 정보 및 객체들이 문서에서 차지하는 영역 정보를 가진 요소
         - treatAsChar: 글자처럼 취급 여부
         - affectLSpacing: 줄 간격에 영향을 줄지 여부. treatAsChar 속성이 "true"일 때만 적용
         - flowWithText: 오브젝트의 세로 위치를 본문 영역으로 제한할지 여부. 하위 요소 RelativeTo의 속성 중 "vertical"이 "PARA"일 때만 사용
         - allowOverlap: 다른 오브젝트와 겹치는 것을 허용할지 여부. treatAsChar 속성이 "false"일 때만 사용. flowWithText 속성이 "true"면 무조건 "false"로 간주함
         - holdAnchorAndSO: 객체와 조판부호를 항상 같은 데 놓을지 여부
         - vertRelTo: 세로 위치의 기준. treatAsChar 속성이 "false"일 때만 사용
         - horzRelTo: 가로 위치의 기준. treatAsChar 속성이 "false"일 때만 사용
         - vertAlign: vertRelTo에 관한 상대적인 배열 방식. vertRelTo의 값에 따라 가능한 범위가 제한됨
            * TOP: 위 (vertRelTo="PAPER"|"PAGE"|"PARA")
            * CENTER: 가운데 (vertRelTo="PAPER"|"PAGE")
            * BOTTOM: 아래 (vertRelTo="PAPER"|"PAGE")
            * INSIDE: 안쪽 (vertRelTo="PAPER"|"PAGE")
         - horzAlign: horzRelTo에 대한 상대적인 배열 방식
         - vertOffset: vertRelTo와 verAlign을 기준점으로 한 상대적인 오프셋 값. 단위는 HWPUNIT.
         - horzOffset: horzRelTo와 horzAlign을 기준점으로 한 상대적인 오프셋 값. 단위는 HWPUNIT.
    */
    private void setPos(){
        picture.createPos();
        picture.pos().treatAsCharAnd(true).affectLSpacingAnd(false).flowWithTextAnd(true).allowOverlapAnd(false).holdAnchorAndSOAnd(false)
                .vertRelToAnd(VertRelTo.PARA).horzRelToAnd(HorzRelTo.PARA).vertAlignAnd(VertAlign.TOP).horzAlignAnd(HorzAlign.LEFT).vertOffsetAnd((long) 0).horzOffsetAnd((long) 0);
    }

    /*
        inMargin: 바깥쪽 여백 정보
         - left: 왼쪽 여백. 단위는 HWPUNIT.
         - right: 오른쪽 여백. 단위는 HWPUNIT.
         - top: 위쪽 여백. 단위는 HWPUNIT.
         - bottom: 아래쪽 여백. 단위는 HWPUNIT.
    */
    private void setOutMargin(){
        picture.createOutMargin();
        picture.outMargin().leftAnd((long)0).rightAnd((long)0).topAnd((long)0).bottomAnd((long)0);
    }

    public Picture render(){
        setPic();
        setOffset();
        setOrgSz();
        setCurSz();
        setFilp();
        setRotationInfo();
        setRenderingInfo();
        setImage();
        setImageRect();
        setImageClip();
        setInMargin();
        setImageDim();
        setEffects();
        setSz();
        setPos();
        setOutMargin();

        return picture;
    }
}
