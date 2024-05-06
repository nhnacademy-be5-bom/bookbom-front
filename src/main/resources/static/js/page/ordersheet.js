function toggleTable() {
    var table = document.querySelector('table'); // 테이블 요소 선택
    if (table.style.display === 'none' || table.style.display === '') {
        table.style.display = 'block'; // 테이블을 보이도록 설정
    } else {
        table.style.display = 'none'; // 테이블을 숨기도록 설정
    }
}


// function selectPaymentMethod(button) {
//     var buttons = document.querySelectorAll('.payment button');
//     buttons.forEach(function (btn) {
//         btn.classList.remove('selected');
//     });
//     button.classList.add('selected');
// }

// function showCreditCardSelect() {
//     var selectElement = document.getElementById('creditCardSelect');
//     var button = document.getElementById('creditCardBtn');
//
//     if (selectElement.style.display === 'none' || selectElement.style.display === '') {
//         selectElement.style.display = 'block'; // 카드 선택을 표시
//     } else {
//         selectElement.style.display = 'none'; // 카드 선택을 숨김
//     }
// }
//
// function hideCreditCardSelect() {
//     var selectElement = document.getElementById('creditCardSelect');
//     if (selectElement) {
//         selectElement.style.display = 'none';
//     }
// }

function selectDeliveryDate(button) {
    var buttons = document.querySelectorAll('.delivery-select button');
    buttons.forEach(function (btn) {
        btn.classList.remove('selected');
    });
    button.classList.add('selected');

}

function calculateProductAmount() {
    var productAmount = 0;

    // 각 book-info 클래스를 가진 tr 요소에 대해 반복합니다.
    document.querySelectorAll('.book-info').forEach(function (element) {
        // 해당 요소 내의 수량과 가격을 가져옵니다.
        var quantityElement = element.querySelector('.quantity');
        var bookCostElement = element.querySelector('.bookCost');

        // 수량과 가격이 모두 존재하는 경우에만 계산을 수행합니다.
        if (quantityElement && bookCostElement) {
            var quantity = parseFloat(quantityElement.textContent);
            var bookCost = parseFloat(bookCostElement.textContent.replace(/[^0-9.-]+/g, ""));
            productAmount += quantity * bookCost;
        }
    });

    // 계산된 상품 금액을 상품 금액 요소에 할당합니다.
    document.getElementById('productAmount').innerText = productAmount.toLocaleString() + ' 원';
}


function calculateDiscountAmount() {
    var discountAmount = 0;

    document.querySelectorAll('.book-info').forEach(function (element) {
        // 해당 요소 내의 수량과 가격을 가져옵니다.
        var quantityElement = element.querySelector('.quantity');
        var bookDiscountCostElement = element.querySelector('.bookDiscountCost');
        if (quantityElement && bookDiscountCostElement) {
            var quantity = parseFloat(quantityElement.textContent);
            var bookDiscountCost = parseFloat(bookDiscountCostElement.textContent.replace(/[^0-9.-]+/g, ""));
            discountAmount += quantity * bookDiscountCost;
        }
    });

    // 계산된 상품 금액을 상품 금액 요소에 할당합니다.
    document.getElementById('discountCost').innerText = discountAmount.toLocaleString() + ' 원';
}

function calculateFinalPayment() {

    // discountAmount 요소의 텍스트 값을 받아와서 더합니다.
    var discountAmount = parseFloat(document.getElementById('discountCost').textContent.replace(/[^0-9.-]+/g, ""));

// wrapCost 요소의 텍스트 값을 받아와서 더합니다.
    var wrapCost = parseFloat(document.getElementById('wrapCost').textContent.replace(/[^0-9.-]+/g, ""));

// deliveryCost 요소의 텍스트 값을 받아와서 더합니다.
    var deliveryCost = parseFloat(document.getElementById('deliveryCost').textContent.replace(/[^0-9.-]+/g, ""));

// 최종 결제 금액 계산
    var finalPayment = discountAmount + wrapCost + deliveryCost;

// 계산된 최종 결제 금액을 HTML에 표시합니다.
    document.getElementById('finalPayment').innerText = finalPayment.toLocaleString() + ' 원';

}

function validatePhoneNumber() {
    var phoneNumberInput = document.getElementById('phone-number');
    var phoneErrorSpan = document.getElementById('phone-error');

    if (phoneNumberInput.value.trim() === '') {
        phoneErrorSpan.innerText = '전화번호를 입력해주세요';
        phoneNumberInput.style.border = '1px solid red';
        phoneErrorSpan.style.display = 'inline';

        return false
    } else if (!/^\d{3}-\d{4}-\d{4}$/.test(phoneNumberInput.value)) {
        phoneErrorSpan.innerText = '올바른 전화번호를 입력하세요.';
        phoneNumberInput.style.border = '1px solid red';
        phoneErrorSpan.style.display = 'inline';
        return false;
    } else {
        phoneErrorSpan.innerText = '';
        phoneNumberInput.style.border = '';
        phoneErrorSpan.style.display = 'none';
        return true;
    }
}

function validateName() {
    var nameInput = document.getElementById('name');
    var nameErrorSpan = document.getElementById('name-error');

    if (nameInput.value.trim() === '') {
        nameErrorSpan.innerText = '이름을 입력해주세요';
        nameInput.style.border = '1px solid red';
        nameErrorSpan.style.display = 'inline';
        return false;
    } else if (!/^[가-힣]{1,50}$/.test(nameInput.value)) {
        nameErrorSpan.innerText = '올바른 이름을 입력하세요';
        nameInput.style.border = '1px solid red';
        nameErrorSpan.style.display = 'inline';
        return false;
    } else {
        nameErrorSpan.innerText = '';
        nameInput.style.border = '';
        nameErrorSpan.style.display = 'none';
        return true;
    }
}

function validateEmail() {
    var emailInput = document.getElementById('email');
    var emailErrorSpan = document.getElementById('email-error');
    var email = emailInput.value.trim(); // 앞뒤 공백 제거

    if (email === '') {
        emailErrorSpan.innerText = '이메일을 입력해주세요';
        emailInput.style.border = '1px solid red';
        emailErrorSpan.style.display = 'inline'; // 에러 메시지 표시
        return false;
    } else if (!/\S+@\S+\.\S+/.test(email)) { // 이메일 형식 확인
        emailErrorSpan.innerText = '올바른 이메일을 입력하세요';
        emailInput.style.border = '1px solid red';
        emailErrorSpan.style.display = 'inline'; // 에러 메시지 표시
        return false;
    } else {
        emailErrorSpan.innerText = '';
        emailInput.style.border = '';
        emailErrorSpan.style.display = 'none'; // 에러 메시지 숨기기
        return true;
    }
}

function validatePassword() {
    var passwordInput = document.getElementById('password');
    var passwordErrorSpan = document.getElementById('password-error');
    var password = passwordInput.value.trim(); // 앞뒤 공백 제거

    if (password === '') {
        passwordErrorSpan.innerText = '비밀번호를 입력해주세요';
        passwordInput.style.border = '1px solid red';
        passwordErrorSpan.style.display = 'inline'; // 에러 메시지 표시
        return false;
    } else if (!/^(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).{8,20}$/.test(password)) { // 영어, 특수문자 포함하여 8자리 이상 확인
        passwordErrorSpan.innerText = '비밀번호는 영어와 특수문자를 포함하여 8자리 이상이어야 합니다';
        passwordInput.style.border = '1px solid red';
        passwordErrorSpan.style.display = 'inline'; // 에러 메시지 표시
        return false;
    } else {
        passwordErrorSpan.innerText = '';
        passwordInput.style.border = '';
        passwordErrorSpan.style.display = 'none'; // 에러 메시지 숨기기
        return true;
    }
}

function validateDelivery() {
    var zipcodeInput = document.getElementById('zip-code');
    var deliveryAddressinput = document.getElementById('delivery-address');
    var addressDetailInput = document.getElementById('address-detail');
    var deliveryErrorSpan = document.getElementById('delivery-error');

    var zipcode = zipcodeInput.value.trim();
    var deliveryAddress = deliveryAddressinput.value.trim();
    var addressDetail = addressDetailInput.value.trim();
    if (!zipcode || !deliveryAddress || !addressDetail) {
        deliveryErrorSpan.innerText = '배송지를 입력해주세요';
        zipcodeInput.style.border = '1px solid red';
        deliveryAddressinput.style.border = '1px solid red';
        addressDetailInput.style.border = '1px solid red';
        deliveryErrorSpan.style.display = 'inline';
        return false;
    } else {
        deliveryErrorSpan.innerText = '';
        zipcodeInput.style.border = '';
        deliveryAddressinput.style.border = '';
        addressDetailInput.style.border = '';
        deliveryErrorSpan.style.display = 'none';
        return true;
    }
}

function validateDeliveryAndProceed() {
    var selectedButton = document.querySelector('.delivery-select button.selected');

    if (!selectedButton) {
        alert('도착 예상일을 선택해주세요'); // 경고창 표시
        return false;
    } else {
        const form = document.createElement('form');
        form.method = 'post';
        form.action = '/order';

        const bookInfos = document.querySelectorAll('.book-info');
        bookInfos.forEach((bookInfo, index) => {
            const titleElement = bookInfo.querySelector('.book-title');
            const quantityElement = bookInfo.querySelector('.quantity');

            const bookId = titleElement.id;
            const quantity = quantityElement.textContent.trim().replace('개', '');
            const wrapperName = quantityElement.id;

            //bookId
            const bookIdInput = document.createElement('input');
            bookIdInput.type = 'hidden';
            bookIdInput.name = `wrapperSelectRequestList[${index}].bookId`;
            bookIdInput.value = bookId;
            form.appendChild(bookIdInput);

            //quantiy
            const quantityInput = document.createElement('input');
            quantityInput.type = 'hidden';
            quantityInput.name = `wrapperSelectRequestList[${index}].quantity`;
            quantityInput.value = quantity;
            form.appendChild(quantityInput);

            //wrapperName
            const wrapperNameInput = document.createElement('input');
            wrapperNameInput.type = 'hidden';
            wrapperNameInput.name = `wrapperSelectRequestList[${index}].wrapperName`;
            wrapperNameInput.value = wrapperName;
            form.appendChild(wrapperNameInput);

        });
        //name
        const name = document.getElementById('name').value;
        const nameInput = document.createElement('input');
        nameInput.type = 'hidden';
        nameInput.name = `name`;
        nameInput.value = name;
        form.appendChild(nameInput);

        //phoneNumber
        const phoneNumber = document.getElementById('phone-number').value;
        const phoneNumberInput = document.createElement('input');
        phoneNumberInput.type = 'hidden';
        phoneNumberInput.name = `phoneNumber`;
        phoneNumberInput.value = phoneNumber;
        form.appendChild(phoneNumberInput);

        //totalCost
        const totalCostElement = document.getElementById('finalPayment').innerText.trim().replace('원', '');
        const totalCost = parseInt(totalCostElement.trim().replace(',', ''));
        const totalCostInput = document.createElement('input');
        totalCostInput.type = 'hidden';
        totalCostInput.name = `totalCost`;
        totalCostInput.value = totalCost;
        form.appendChild(totalCostInput);

        //discountCost
        const discountCostElement = document.getElementById('discountCost').innerText.trim().replace('원', '');
        const discountCost = parseInt(discountCostElement.trim().replace(',', ''));
        const discountCostInput = document.createElement('input');
        discountCostInput.type = 'hidden';
        discountCostInput.name = `discountCost`;
        discountCostInput.value = discountCost;
        form.appendChild(discountCostInput);

        //email
        const email = document.getElementById('email').value;
        const emailInput = document.createElement('input');
        emailInput.type = 'hidden';
        emailInput.name = `email`;
        emailInput.value = email;
        form.appendChild(emailInput);

        //password
        const password = document.getElementById('password').value;
        const passwordInput = document.createElement('input');
        passwordInput.type = 'hidden';
        passwordInput.name = `password`;
        passwordInput.value = password;
        form.appendChild(passwordInput);

        //estimatedDateTostring
        // const estimatedDateTostring = document.querySelector('.delivery-select button:checked').value;
        const button = document.querySelector('.delivery-select button.selected');
        const estimatedDateTostring = button.value;
        // let estimatedDateTostring = null;
        // buttons.forEach(button => {
        //     if (button.checked) {
        //         estimatedDateTostring = button.value;
        //     }
        // });
        const estimatedDateTostringInput = document.createElement('input');
        estimatedDateTostringInput.type = 'hidden';
        estimatedDateTostringInput.name = `estimatedDateTostring`;
        estimatedDateTostringInput.value = estimatedDateTostring;
        form.appendChild(estimatedDateTostringInput);

        //deliveryCost
        const deliveryCost = parseInt(document.getElementById('deliveryCost').textContent.trim().replace('원', ''));
        const deliveryCostInput = document.createElement('input');
        deliveryCostInput.type = 'hidden';
        deliveryCostInput.name = `deliveryCost`;
        deliveryCostInput.value = deliveryCost;
        form.appendChild(deliveryCostInput);

        //zipCode
        const zipCode = document.getElementById('zip-code').value;
        const zipCodeInput = document.createElement('input');
        zipCodeInput.type = 'hidden';
        zipCodeInput.name = `zipCode`;
        zipCodeInput.value = zipCode;
        form.appendChild(zipCodeInput);

        //deliveryAddress
        const deliveryAddress = document.getElementById('delivery-address').value;
        const deliveryAddressInput = document.createElement('input');
        deliveryAddressInput.type = 'hidden';
        deliveryAddressInput.name = `deliveryAddress`;
        deliveryAddressInput.value = deliveryAddress;
        form.appendChild(deliveryAddressInput);

        //addressDetail
        const addressDetail = document.getElementById('address-detail').value;
        const addressDetailInput = document.createElement('input');
        addressDetailInput.type = 'hidden';
        addressDetailInput.name = `addressDetail`;
        addressDetailInput.value = addressDetail;
        form.appendChild(addressDetailInput);


        document.body.appendChild(form);
        form.submit();
        return true;
    }
}
