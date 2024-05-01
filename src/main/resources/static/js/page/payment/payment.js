const paymentSelectButtons = document.querySelectorAll('.payment-select');
const paymentButton = document.getElementById('payment-button');
const tossPayButton = document.getElementById('toss-pay');

paymentSelectButtons.forEach(button => {
    button.addEventListener('click', () => {
        // 현재 클릭된 버튼에 active 클래스를 추가
        paymentSelectButtons.forEach(btn => {
            btn.classList.remove('active');
            btn.style.border = '1px solid #ffffff';
        });
        button.classList.add('active');
        button.style.border = '2px solid #333d4b';

        // 결제하기 버튼을 활성화
        paymentButton.disabled = false;
    });
});


var clientKey = 'test_ck_ex6BJGQOVDbEXGJvJJ558W4w2zNb'
var tossPayments = TossPayments(clientKey) // 클라이언트 키로 초기화하기

// orderId 가져오기
var orderId = document.getElementById("order-id").value;

// orderName 가져오기
var orderName = document.getElementById("order-name").value;

// amount 가져오기
var amount = document.getElementById("amount").value;

function handlePayment() {
    const isTossPaySelected = tossPayButton.classList.contains('active');
    if (isTossPaySelected) {
        // 토스 페이로 결제 처리 로직 추가
        // window.location.href = '/toss-pay';
        // window.open('/toss-pay', '_blank');
        tossPayments
            .requestPayment('카드', {
                // 결제수단
                // 결제 정보
                amount: amount,
                orderId: orderId,
                orderName: orderName,
                customerName: '김아무개',
                successUrl: 'http://localhost:8020/toss-success',
                failUrl: 'http://localhost:8020/toss-fail',
                flowMode: 'DIRECT',
                easyPay: '토스페이',

            })
            .catch(function (error) {
                if (error.code === 'USER_CANCEL') {
                    // 결제 고객이 결제창을 닫았을 때 에러 처리
                } else if (error.code === 'INVALID_CARD_COMPANY') {
                    // 유효하지 않은 카드 코드에 대한 에러 처리
                }
            })

    } else {
        console.log('토스 페이로 선택되지 않았습니다.');
        // 토스 페이가 선택되지 않은 경우에 대한 처리 추가
    }
}


//     const paymentSelectButtons = document.querySelectorAll('.payment-select');
//     const paymentButton = document.getElementById('payment-button');
//     const tossPayButton = document.getElementById('toss-pay');
//
//     paymentSelectButtons.forEach(button => {
//         button.addEventListener('click', () => {
//             // 현재 클릭된 버튼에 active 클래스를 추가
//             paymentSelectButtons.forEach(btn => {
//                 btn.classList.remove('active');
//                 btn.style.border = '1px solid #ffffff';
//             });
//             button.classList.add('active');
//             button.style.border = '2px solid #333d4b';
//
//             // 결제하기 버튼을 활성화
//             paymentButton.disabled = false;
//         });
//     });
//
//     function handlePayment() {
//         const isTossPaySelected = tossPayButton.classList.contains('active');
//         if (isTossPaySelected) {
//             // 토스 페이로 결제 처리 로직 추가
//             // window.location.href = '/toss-pay';
//             window.open('/toss-pay', '_blank');
//
// //         const currentURL = window.location.protocol + "//" + window.location.host + "/" + window.location.pathname.split("/")[1];
// // // const generateRandomString = () => window.btoa(Math.random()).slice(0, 20);
// //         var amount = 50000;
// //
// //         const clientKey = "test_ck_ex6BJGQOVDbEXGJvJJ558W4w2zNb";
// // // const customerKey = generateRandomString();
// // // const paymentWidget = PaymentWidget(clientKey, customerKey); // 회원 결제
// //         const paymentWidget = PaymentWidget(clientKey, PaymentWidget.ANONYMOUS); // 비회원 결제
// //         console.log('토스 페이로 결제합니다.');
// //         paymentWidget.requestPayment({
// //             orderId: generateRandomString(),
// //             orderName: "토스 티셔츠 외 2건",
// //             successUrl: "http://localhost:8020/toss/success",
// //             failUrl: "http://localhost:8020/toss/fail",
// //             customerEmail: "customer123@gmail.com",
// //             customerName: "김토스",
// //             customerMobilePhone: "01012341234",
// //         });
//
//         } else {
//             console.log('토스 페이로 선택되지 않았습니다.');
//             // 토스 페이가 선택되지 않은 경우에 대한 처리 추가
//         }
//     }
