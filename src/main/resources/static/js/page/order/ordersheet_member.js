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
    couponNameByDiscountType();
    updateDeliveryCost();
    calculateFinalPayment();
    // 옵션 선택에 대한 이벤트 리스너 추가
    var selectElement = document.getElementById("selected-coupon"); // 옵션을 담고 있는 셀렉트 요소의 ID를 가져와야 합니다.
    selectElement.addEventListener("change", function (event) {
        // var selectedCouponId = event.target.value; // 선택된 옵션의 값 (쿠폰 ID)을 가져옵니다.
        handleCouponSelection(event.target); // 선택된 쿠폰 ID를 처리하는 함수 호출
    });
});
// 변화를 감지할 대상 요소와 옵션 설정
// var observer = new MutationObserver(function (mutationsList, observer) {
//     // 최종 결제 금액이 변경되면 포장비 업데이트 함수 실행
//     updateDeliveryCost();
// });

// finalPaymentElement.addEventListener('change', updateDeliveryCost);

// 최종 결제 금액이 변경될 때마다 실행할 함수
function updateDeliveryCost() {
    const totalCostElement = document.getElementById('finalPayment').innerText.trim().replace('원', '');
    const totalCost = parseInt(totalCostElement.trim().replace(',', ''));
    var deliveryCostElement = document.getElementById('deliveryCost');
    if (totalCost < 30000) {
        deliveryCostElement.textContent = '5000 원'; // 30000원 초과 시 포장비 5000원으로 설정
    } else {
        deliveryCostElement.textContent = '0 원'; // 30000원 이하 시 포장비 0원으로 설정
    }
}

function couponNameByDiscountType() {
    // usable-coupon 클래스를 가진 옵션 요소를 선택합니다.
    const usableOptions = document.querySelectorAll('.usable-coupon');

    // 각 옵션에 대해 작업합니다.
    usableOptions.forEach(function (Uoption) {
        // 옵션의 id 값을 가져옵니다.
        const discountType = Uoption.id;
        // 옵션의 텍스트 값을 가져옵니다.
        let text = Uoption.textContent;
        // 선택된 텍스트가 '원'이나 '%'으로 끝나는 경우 함수를 종료합니다.
        if (text.endsWith('원') || text.endsWith('%')) {
            return;
        }
        // 할인 타입에 따라 텍스트를 조정합니다.
        if (discountType === 'COST') {
            text += ' 원 할인';
        } else if (discountType === 'RATE') {
            text += ' % 할인';
        }
        // 변경된 텍스트를 옵션에 적용합니다.
        Uoption.textContent = text;
    });

    const disableOptions = document.querySelectorAll('.disable-coupon');
    // 각 옵션에 대해 작업합니다.
    disableOptions.forEach(function (Doption) {
        // 옵션의 id 값을 가져옵니다.
        const discountType = Doption.id;
        // 옵션의 텍스트 값을 가져옵니다.
        let text = Doption.textContent;
        // 할인 타입에 따라 텍스트를 조정합니다.
        if (discountType === 'COST') {
            text += ' 원 할인';
        } else if (discountType === 'RATE') {
            text += ' % 할인';
        }
        // 변경된 텍스트를 옵션에 적용합니다.
        Doption.textContent = text;
    });

}


function handleCouponSelection(target) {
    var selectedText = target.options[target.selectedIndex].text; // 선택된 옵션의 텍스트를 가져옵니다.
    var couponCostElement = document.getElementById('usedCouponCost');

    if (selectedText === '선택 안함') {
        couponCostElement.innerText = '- 0 원';
        calculateFinalPayment();
        updateDeliveryCost();
        calculateFinalPayment();
        return;
    }
    var selectedDiscountType = target.options[target.selectedIndex].id;
    var selectedMaxCost = target.options[target.selectedIndex].getAttribute("name");
    var couponDiscount = selectedText.replace(/[^0-9.-]+/g, "");

    var discountCost = document.getElementById('discountCost').textContent.replace(/[^0-9.-]+/g, "");
    var finalPayment = parseFloat(document.getElementById('finalPayment').textContent.replace(/[^0-9.-]+/g, ""));

    if (selectedDiscountType === 'COST') {
        if (couponDiscount > selectedMaxCost) {
            alert('쿠폰 최대 할인 금액을 초과했습니다. 다른 쿠폰을 사용해주세요.');
            couponCostElement.innerText = '- 0 원';
            return;
        } else if (couponDiscount > finalPayment) {
            alert('쿠폰 최대 할인 금액이 주문 금액을 초과했습니다. 최대 할인 금액을 적용합니다');
            couponCostElement.innerText = '- ' + finalPayment + ' 원';
            calculateFinalPayment();
            updateDeliveryCost();
            calculateFinalPayment();
            return;
        }

        couponCostElement.innerText = '- ' + couponDiscount + ' 원';


    } else if (selectedDiscountType === 'RATE') {

        var discountAmount = Math.floor(parseFloat(discountCost) * parseFloat(couponDiscount) / 100);
        if (discountAmount > selectedMaxCost) {
            alert('쿠폰 최대 할인 금액을 초과했습니다. 다른 쿠폰을 사용해주세요.');
            couponCostElement.innerText = '- 0 원';
            return;
        }
        couponCostElement.innerText = '- ' + discountAmount + ' 원';
    }
    calculateFinalPayment();
    updateDeliveryCost();
    calculateFinalPayment();

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
    var usedCouponCost = parseFloat(document.getElementById('usedCouponCost').textContent.replace(/[^0-9.-]+/g, ""));
    var usedPoint = parseFloat(document.getElementById('usedPoint').textContent.replace(/[^0-9.-]+/g, ""));

// 최종 결제 금액 계산
    var finalPayment = discountAmount + wrapCost + deliveryCost + usedCouponCost + usedPoint;

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
        fetch('/users/check-email?email=' + encodeURIComponent(email))
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
                idErrorSpan.innerText = '서버 오류가 발생했습니다. 관리자에게 문의 주세요.';
                idInput.style.border = '1px solid red';
                idErrorSpan.style.display = 'inline'; // 에러 메시지 표시
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

function usePoint() {
    var pointInput = document.getElementById('point');
    var myPointSpan = document.getElementById('my-point');
    var myPointText = myPointSpan.textContent.replace('P', ''); // "P" 부분을 제거
    var myPoint = parseInt(myPointText.trim()); // 공백 제거 후 숫자로 변환
    var finalPayment = parseFloat(document.getElementById('finalPayment').textContent.replace(/[^0-9.-]+/g, ""));
    var usedPointElement = document.getElementById('usedPoint');

    if (pointInput.value.trim() === '') {
        alert('포인트를 입력해주세요');
        pointInput.value = '0'; // 입력값이 없는 경우 0으로 설정
        return false;
    } else if (!/^\d+$/.test(pointInput.value)) {
        alert('숫자만 입력 가능합니다');
        pointInput.value = '0'; // 숫자가 아닌 경우 0으로 설정
        return false;
    } else if (parseInt(pointInput.value) > myPoint) {
        alert('보유 포인트 이하로 입력해주세요');
        pointInput.value = '0'; // 보유 포인트 이상의 값이 입력된 경우 0으로 설정
        return false;
    } else if (parseInt(pointInput.value) > finalPayment) {
        alert('주문 가격을 초과했습니다. 최대 할인 금액을 적용합니다.');
        pointInput.value = finalPayment;
        usedPointElement.innerText = '- ' + finalPayment + ' P';
        calculateFinalPayment();
        updateDeliveryCost();
        calculateFinalPayment();
        return false;
    } else {

        usedPointElement.innerText = '- ' + pointInput.value + ' P';
        calculateFinalPayment();
        updateDeliveryCost();
        calculateFinalPayment();
        return true;
    }
}

var childWindow;

function myAddressesPopup() {
    // 주소록 페이지의 URL
    var addressBookUrl = '/users/my-address';

    // 팝업 창을 띄우기
    childWindow = window.open(addressBookUrl, '_blank', 'width=600,height=400,toolbar=0,location=0,menubar=0,status=0,scrollbars=1,resizable=1');
}

function receiveSelectedAddress(zipCode, address, addressDetail) {
    // 받은 주소 정보를 사용하여 작업 수행
    console.log("자식 창에서 받은 우편번호:", zipCode);
    console.log("자식 창에서 받은 주소:", address);
    console.log("자식 창에서 받은 상세 주소:", addressDetail);

    childWindow.close();
    var zipcodeInput = document.getElementById('sample6_postcode');
    var deliveryAddressinput = document.getElementById('sample6_address');
    var addressDetailInput = document.getElementById('sample6_detailAddress');
    zipcodeInput.value = zipCode;
    deliveryAddressinput.value = address;
    addressDetailInput.value = addressDetail;
}

function validateDeliveryAndProceed() {
    var selectedButton = document.querySelector('.delivery-select button.selected');

    if (!selectedButton) {
        alert('도착 예상일을 선택해주세요'); // 경고창 표시
        return false;
    } else {
        const form = document.createElement('form');
        form.method = 'post';
        form.action = '/order-member';

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

        //usedPoint
        const usedPointElement = document.getElementById('usedPoint').textContent.replace(/[^0-9.-]+/g, "");
        const usedPoint = parseInt(usedPointElement.replace('-', ''));
        const usedPointInput = document.createElement('input');
        usedPointInput.type = 'hidden';
        usedPointInput.name = `usedPoint`;
        usedPointInput.value = usedPoint;
        form.appendChild(usedPointInput);

        //usedCouponCost
        const usedCouponCostElement = document.getElementById('usedCouponCost').textContent.replace(/[^0-9.-]+/g, "");
        const usedCouponCost = parseInt(usedCouponCostElement.replace('-', ''));
        const usedCouponCostInput = document.createElement('input');
        usedCouponCostInput.type = 'hidden';
        usedCouponCostInput.name = `usedCouponCost`;
        usedCouponCostInput.value = usedCouponCost;
        form.appendChild(usedCouponCostInput);

        //couponId
        const selectElement = document.getElementById('selected-coupon');
        const selectedOption = selectElement.options[selectElement.selectedIndex];
        var couponId = selectedOption.value;
        if (couponId === 'null') {
            couponId = parseInt('0');
        } else {
            couponId = parseInt(selectedOption.value);
        }
        const couponIdInput = document.createElement('input');
        couponIdInput.type = 'hidden';
        couponIdInput.name = `couponId`;
        couponIdInput.value = couponId;
        form.appendChild(couponIdInput);

        document.body.appendChild(form);
        form.submit();
        return true;
    }
}
