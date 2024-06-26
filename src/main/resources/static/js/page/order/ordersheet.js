function toggleTable() {
    var table = document.querySelector('table'); // 테이블 요소 선택
    if (table.style.display === 'none' || table.style.display === '') {
        table.style.display = 'block'; // 테이블을 보이도록 설정
    } else {
        table.style.display = 'none'; // 테이블을 숨기도록 설정
    }
}


function selectDeliveryDate(button) {
    var buttons = document.querySelectorAll('.delivery-select button');
    buttons.forEach(function (btn) {
        btn.classList.remove('selected');
    });
    button.classList.add('selected');

}

document.addEventListener('DOMContentLoaded', function () {
    calculateProductAmount();
    calculateDiscountAmount();
    calculateFinalPayment();
});

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

// function validateId() {
//     var idInput = document.getElementById('id');
//     var idErrorSpan = document.getElementById('id-error');
//     var id = idInput.value.trim(); // 앞뒤 공백 제거
//     var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
//     if (id === '') {
//         idErrorSpan.innerText = '아이디를 입력해주세요.';
//         idInput.style.border = '1px solid red';
//         idErrorSpan.style.display = 'inline'; // 에러 메시지 표시
//         return false;
//     } else if (!emailPattern.test(id)) { // 아이디 형식 확인
//         idErrorSpan.innerText = '올바른 이메일 형식을 입력하세요.';
//         idInput.style.border = '1px solid red';
//         idErrorSpan.style.display = 'inline'; // 에러 메시지 표시
//         return false;
//     } else {
//
//     }
// }

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

        fetch('/check-email?email=' + encodeURIComponent(email))
            .then(response => response.json())
            .then(data => {
                if (!data.result.canUse) {
                    emailErrorSpan.innerText = '이미 가입된 이메일입니다.';
                    emailInput.style.border = '1px solid red';
                    emailErrorSpan.style.display = 'inline'; // 에러 메시지 표시
                    return false;
                } else {
                    emailErrorSpan.innerText = '';
                    emailInput.style.border = '';
                    emailErrorSpan.style.display = 'none'; // 에러 메시지 숨기기
                    return true;
                }
            })
            .catch(error => {
                // console.error('Error:', error);
                emailErrorSpan.innerText = '서버 오류가 발생했습니다. 관리자에게 문의 주세요.';
                emailInput.style.border = '1px solid red';
                emailErrorSpan.style.display = 'inline'; // 에러 메시지 표시
                return true;
            });
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
    var zipcodeInput = document.getElementById('sample6_postcode');
    var deliveryAddressinput = document.getElementById('sample6_address');
    var addressDetailInput = document.getElementById('sample6_detailAddress');
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

//주소찾기 api
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            // if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            //     addr = data.roadAddress;
            // } else { // 사용자가 지번 주소를 선택했을 경우(J)
            //     addr = data.jibunAddress;
            // }
            addr = data.roadAddress;

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === 'R') {
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;

            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
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
            if (bookId === '') {
                alert('책 아이디를 찾을 수 없습니다. 새로고침 후 다시시도해주세요.')
            }
            const bookIdInput = document.createElement('input');
            bookIdInput.type = 'hidden';
            bookIdInput.name = `wrapperSelectRequestList[${index}].bookId`;
            bookIdInput.value = bookId;
            form.appendChild(bookIdInput);

            //quantity
            if (quantity === '') {
                alert('책 수량을 찾을 수 없습니다. 새로고침 후 다시시도해주세요.')
            }
            const quantityInput = document.createElement('input');
            quantityInput.type = 'hidden';
            quantityInput.name = `wrapperSelectRequestList[${index}].quantity`;
            quantityInput.value = quantity;
            form.appendChild(quantityInput);

            //wrapperName
            if (wrapperName === '') {
                alert('책 포장지를 찾을 수 없습니다. 새로고침 후 다시시도해주세요.')
            }
            const wrapperNameInput = document.createElement('input');
            wrapperNameInput.type = 'hidden';
            wrapperNameInput.name = `wrapperSelectRequestList[${index}].wrapperName`;
            wrapperNameInput.value = wrapperName;
            form.appendChild(wrapperNameInput);

        });
        //name
        const name = document.getElementById('name').value;
        if (name.trim() === '') {
            alert('이름을 입력해주세요.');
            return false;
        } else if (!validateName()) {
            alert('올바른 이름을 입력하세요.');
            return false;
        }
        const nameInput = document.createElement('input');
        nameInput.type = 'hidden';
        nameInput.name = `name`;
        nameInput.value = name;
        form.appendChild(nameInput);
        //phoneNumber
        const phoneNumber = document.getElementById('phone-number').value;
        if (phoneNumber.trim() === '') {
            alert('전화번호를 입력해주세요');
            return false;
        } else if (!validatePhoneNumber()) {
            alert('올바른 전화번호를 입력하세요.');
            return false;
        }
        const phoneNumberInput = document.createElement('input');
        phoneNumberInput.type = 'hidden';
        phoneNumberInput.name = `phoneNumber`;
        phoneNumberInput.value = phoneNumber;
        form.appendChild(phoneNumberInput);

        //totalCost
        const totalCostElement = document.getElementById('finalPayment').innerText.trim().replace('원', '');
        const totalCost = parseInt(totalCostElement.trim().replace(',', ''));
        if (totalCost === 0) {
            alert('주문 금액이 0원입니다. 새로고침 후 다시 시도해주세요.');
            return false;
        }
        const totalCostInput = document.createElement('input');
        totalCostInput.type = 'hidden';
        totalCostInput.name = `totalCost`;
        totalCostInput.value = totalCost;
        form.appendChild(totalCostInput);

        //discountCost
        const discountCostElement = document.getElementById('discountCost').innerText.trim().replace('원', '');
        const discountCost = parseInt(discountCostElement.trim().replace(',', ''));
        if (discountCost === 0) {
            alert('할인 금액이 0원입니다. 새로고침 후 다시 시도해주세요.');
            return false;
        }
        const discountCostInput = document.createElement('input');
        discountCostInput.type = 'hidden';
        discountCostInput.name = `discountCost`;
        discountCostInput.value = discountCost;
        form.appendChild(discountCostInput);

        //email
        const email = document.getElementById('email').value;
        if (email.trim() === '') {
            alert('이메일을 입력해주세요');
            return false;
        } else if (!validateEmail()) {
            alert('올바른 이메일을 입력하세요');
            return false;
        }
        const emailInput = document.createElement('input');
        emailInput.type = 'hidden';
        emailInput.name = `email`;
        emailInput.value = email;
        form.appendChild(emailInput);

        //password
        const password = document.getElementById('password').value;
        if (password.trim() === '') {
            alert('비밀번호을 입력해주세요');
            return false;
        } else if (!validatePassword()) {
            alert('올바른 비밀번호를 입력하세요');
            return false;
        }
        const passwordInput = document.createElement('input');
        passwordInput.type = 'hidden';
        passwordInput.name = `password`;
        passwordInput.value = password;
        form.appendChild(passwordInput);

        //estimatedDateTostring
        const button = document.querySelector('.delivery-select button.selected');
        const estimatedDateTostring = button.value;
        const estimatedDateTostringInput = document.createElement('input');
        estimatedDateTostringInput.type = 'hidden';
        estimatedDateTostringInput.name = `estimatedDateTostring`;
        estimatedDateTostringInput.value = estimatedDateTostring;
        form.appendChild(estimatedDateTostringInput);

        //deliveryCost
        const deliveryCost = parseInt(document.getElementById('deliveryCost').textContent.trim().replace('원', ''));
        if (deliveryCost === 0) {
            alert('할인 금액이 0원입니다. 새로고침 후 다시 시도해주세요.');
            return false;
        }
        const deliveryCostInput = document.createElement('input');
        deliveryCostInput.type = 'hidden';
        deliveryCostInput.name = `deliveryCost`;
        deliveryCostInput.value = deliveryCost;
        form.appendChild(deliveryCostInput);

        //zipCode
        const zipCode = document.getElementById('sample6_postcode').value;
        if (zipCode.trim() === '') {
            alert('우편번호를 입력해주세요');
            return false;
        }
        const zipCodeInput = document.createElement('input');
        zipCodeInput.type = 'hidden';
        zipCodeInput.name = `zipCode`;
        zipCodeInput.value = zipCode;
        form.appendChild(zipCodeInput);

        //deliveryAddress
        const deliveryAddress = document.getElementById('sample6_address').value;
        if (deliveryAddress.trim() === '') {
            alert('주소를 입력해주세요');
            return false;
        }
        const deliveryAddressInput = document.createElement('input');
        deliveryAddressInput.type = 'hidden';
        deliveryAddressInput.name = `deliveryAddress`;
        deliveryAddressInput.value = deliveryAddress;
        form.appendChild(deliveryAddressInput);

        //addressDetail
        const addressDetail = document.getElementById('sample6_detailAddress').value;
        if (addressDetail.trim() === '') {
            alert('상세주소를 입력해주세요');
            return false;
        }
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
