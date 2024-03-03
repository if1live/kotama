package kr.ne.shiroko.kotama

// TODO: 주소 수정
val webhookUrl = "http://192.168.0.103:3000/"

val packageNames_mobilePayments = listOf(
    "com.samsung.android.spay", // Samsung Wallet (Samsung Pay)
    "com.nhn.android.search",   // 네이버 - NAVER
    "com.kbcard.cxh.appcard",   // KB Pay
)

val packageNames_commercialBank = listOf(
    // 4대 시중은행
    "com.kbstar.kbbank",        // KB국민은행 스타뱅킹
    "com.kebhana.hanapush",     // 하나은행, 하나원큐는 돈기운 가득한 은행 앱
    "com.shinhan.sbanking",     // 신한 SOL뱅크-신한은행 스마트폰 뱅킹
    "com.wooribank.smart.npib", // 우리은행 우리WON뱅킹

    // 외국계 시중은행
    "com.scbank.ma30",              // SC제일은행 모바일뱅킹
    "kr.co.citibank.citimobile",    // 씨티모바일

    // 인터넷 전문 은행
    "com.kbankwith.smartbank",  // 케이뱅크 (Kbank) - make money
    "com.kakaobank.channel",    // 카카오뱅크
    "viva.republica.toss",      // 토스

    // 기타
    "com.epost.psf.sdsi",   // 우체국뱅킹
)

val packageNames_creditCooperatives = listOf(
    "kr.co.cu.onbank",  // 신협ON뱅크

    "nh.smart.banking", // NH스마트뱅킹
    "nh.smart.nhcok",   // NH콕뱅크(농협)

    "com.smg.spbs",     // MG더뱅킹
    "com.smg.mgnoti",   // MG스마트알림
);

val packageNameAllowList = listOf(
    packageNames_mobilePayments,
    packageNames_commercialBank,
    packageNames_creditCooperatives,
).flatten()

// 부분 일치
val bannedPartialTextList = listOf<String>(
    // 일반
    "(광고)",
    "신용점수",

    // 네이버페이
    // [현장결제]포인트뽑기 혜택의 포인트 7원이 적립되었어요.
    "[현장결제]포인트뽑기",

    // "매달 내는 돈이에요." 알림은 여러줄로 구성된다.
    "매달 내는 돈이에요.",

    // 토스
    // 친구가 돈을 받아서 8원 모았어요
    "친구가 돈을 받아서",
    // 확인하지 않은 공지 1건
    "확인하지 않은 공지",
)

// 전체 일치
val bannedFullTextList = listOf<String>(
    // 토스
    "근처에 토스를 켠 사람이 있어요!",
    "한 번에 많이 들어온 금액 발견",
    "사라지기 전에 토스를 켜보세요.",
    "만보기",

    // 삼성페이
    "DroidX 실행 중",
    "Samsung Pay 보호 중입니다",

    // 케이뱅크
    "띵동♪ 이번 달 이자가 도착했어요.",
    "구독료 돌려받기 이벤트 알림",
)
