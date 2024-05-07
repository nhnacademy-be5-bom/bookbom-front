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
        tossPayments
            .requestPayment('카드', {
                // 결제수단
                // 결제 정보
                amount: amount,
                orderId: orderId,
                orderName: orderName,
                successUrl: 'https://bookbom.shop/toss-success',
                failUrl: 'https://bookbom.shop/toss-fail',
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


