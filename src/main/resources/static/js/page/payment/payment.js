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


import {ANONYMOUS, loadPaymentWidget} from "@tosspayments/payment-widget-sdk";

const main = async () => {
    const paymentWidget = await loadPaymentWidget(
        "test_ck_ex6BJGQOVDbEXGJvJJ558W4w2zNb",
        // 비회원 customerKey
        ANONYMOUS
    );
}


const paymentRequestButton = document.getElementById('payment-request-button');

function handlePayment() {
    const isTossPaySelected = tossPayButton.classList.contains('active');
    if (isTossPaySelected) {
        // 토스 페이로 결제 처리 로직 추가
        // window.location.href = '/toss-pay';
        // window.open('/toss-pay', '_blank');
        paymentWidget.requestPayment({
            amount: 30000,
            orderId: 'pNlQfRdsfesfd46',
            orderName: '낮잠자자! 외 1건',
            customerName: '김아무개',
            successUrl: 'http://localhost:8020/toss/success',
            failUrl: 'http://localhost:8020/toss/fail',
            flowMode: 'DIRECT',
            easyPay: '토스페이',
        });

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
