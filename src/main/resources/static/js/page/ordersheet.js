function toggleTable() {
    var table = document.querySelector('table'); // 테이블 요소 선택
    if (table.style.display === 'none' || table.style.display === '') {
        table.style.display = 'block'; // 테이블을 보이도록 설정
    } else {
        table.style.display = 'none'; // 테이블을 숨기도록 설정
    }
}
function redirectToURL(url) {
    window.location.href = url; // 해당 URL로 이동
}
const phoneInput = document.getElementById('phone');
const phoneError = document.getElementById('phone-error');

phoneInput.addEventListener('input', function(event) {
    const phoneNumber = event.target.value;
    const phonePattern = /^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$/;

    if (!phonePattern.test(phoneNumber)) {
        phoneError.style.display = 'inline';
    } else {
        phoneError.style.display = 'none';
    }
});
function selectPaymentMethod(button) {
    var buttons = document.querySelectorAll('.payment button');
    buttons.forEach(function(btn) {
        btn.classList.remove('selected');
    });
    button.classList.add('selected');
}
function showCreditCardSelect() {
    var selectElement = document.getElementById('creditCardSelect');
    var button = document.getElementById('creditCardBtn');

    if (selectElement.style.display === 'none' || selectElement.style.display === '') {
        selectElement.style.display = 'block'; // 카드 선택을 표시
    } else {
        selectElement.style.display = 'none'; // 카드 선택을 숨김
    }
}

function hideCreditCardSelect() {
    var selectElement = document.getElementById('creditCardSelect');
    if (selectElement) {
        selectElement.style.display = 'none';
    }
}
function selectDeliveryDate(button) {
    var buttons = document.querySelectorAll('.delivery-select button');
    buttons.forEach(function(btn) {
        btn.classList.remove('selected');
    });
    button.classList.add('selected');
}
