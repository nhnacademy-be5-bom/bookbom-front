//수정과 완료 버튼 toggle
function toggleFields(event) {
    var button = event.target
    var container = button.closest('.policyContainer');
    var selects = container.querySelectorAll('select');
    var inputs = container.querySelectorAll('input[type="text"]');
    var buttonText = button.innerText.trim();

    selects.forEach(function (select) {
        select.disabled = buttonText === "수정" ? false : true;
    });

    inputs.forEach(function (input) {
        input.readOnly = buttonText === "수정" ? false : true;
    });

    if (button.innerText.trim() === "완료") {
        completeEditing(button);
    }

    button.innerText = buttonText === "수정" ? "완료" : "수정";
}

//수정 요청
function completeEditing(button) {
    var couponPolicyId = button.parentNode.parentNode.id;
    var discountType = document.querySelector('.policyContainer select[name="discountType"]').value.toUpperCase();
    var discountAmount = document.querySelector('.policyContainer input[name="discountCost"]').value;
    var minOrderAmount = document.querySelector('.policyContainer input[name="minOrderCost"]').value;
    var maxDiscountAmount = document.querySelector('.policyContainer input[name="maxDiscountCost"]').value;

    console.log(couponPolicyId);

    if (discountType === '비율할인' && Number(discountAmount) <= 0 || Number(discountAmount) > 100) { //할인 비율이 1~100%를 벗어난 경우
        console.log('비율할인 조건문')
        alert("할인 비율은 1~100사이의 값을 입력해주세요.");
        return;
    }

    if (discountType === '금액할인' && Number(discountAmount) > Number(minOrderAmount)) { //할인 금액은 최소 주문 금액보다 작아야 함
        alert("할인 금액은 최소 주문 금액보다 작아야 합니다.");
        return;
    }

    if (discountType === '금액할인' && discountAmount <= 0) { //할인 금액이 0이거나 음수인 경우
        alert("올바른 할인 금액을 입력해주세요.");
        return;
    }

    if (discountType === '금액할인' && maxDiscountAmount != null) { //비율 할인의 경우만 최대 할인 금액 존재
        alert("최대할인금액은 비율할인에만 적용되며 금액할인에는 적용되지 않습니다.");
        maxDiscountAmount = null;
    }

    fetch('/admin/couponPolicy', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            couponPolicyId: couponPolicyId,
            discountType: (discountType === '금액할인' ? 'COST' : 'RATE'),
            discountCost: discountAmount,
            minOrderCost: minOrderAmount,
            maxDiscountCost: maxDiscountAmount
        })
    }).then(response => {
        if (response.ok) {
            alert("수정이 완료되었습니다.");
            location.reload();
        } else {
            console.error("수정 중 오류가 발생했습니다.");
        }
    }).catch(error => {
        console.error("요청을 보내는 중 오류가 발생했습니다:", error);
    });
}

//선택 정책 삭제
function deleteSelectedContainers() {
    var containers = document.querySelectorAll('.policyContainer');
    containers.forEach(function (container) {
        var checkbox = container.querySelector('input[type="checkbox"]');
        if (checkbox.checked) {
            var checkedId = checkbox.id;
            console.log(checkedId);
            fetch('/admin/couponPolicy', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    couponPolicyId: checkedId
                }),
            }).then(response => {
                return response.json();
            }).then(data => {
                if (data.header.resultMessage == "SUCCESS") {
                    container.remove();
                } else {
                    alert("정책 삭제 실패. 관리자에게 문의하세요.");
                }
            })
        }
    });
}

function showModal() {
    var createPolicyModal = document.querySelector('.createPolicyModal');
    createPolicyModal.style.display = 'block';
}

// 등록 버튼 클릭
function create() {
    var createPolicyModal = document.querySelector('.createPolicyModal');
    var discountType = document.querySelector('.createPolicyModal select[name="discountType"]').value.toUpperCase();
    var discountAmount = document.querySelector('.createPolicyModal input[name="discountAmount"]').value;
    var minOrderAmount = document.querySelector('.createPolicyModal input[name="minOrderAmount"]').value;
    var maxDiscountAmount = document.querySelector('.createPolicyModal input[name="maxDiscountAmount"]').value;

    if (discountType === '비율할인' && discountAmount <= 0 && discountAmount > 100) { //할인 비율이 1~100%를 벗어난 경우
        alert("할인 비율은 1~100사이의 값을 입력해주세요.");
        return;
    }

    if (discountType === '금액할인' && discountAmount > minOrderAmount) { //할인 금액은 최소 주문 금액보다 작아야 함
        alert("할인 금액은 최소 주문 금액보다 작아야 합니다.");
        return;
    }

    if (discountType === '금액할인' && discountAmount <= 0) { //할인 금액이 0이거나 음수인 경우
        alert("올바른 할인 금액을 입력해주세요.");
        return;
    }

    if (discountType === '금액할인' && maxDiscountAmount != null) { //비율 할인의 경우만 최대 할인 금액 존재
        alert("최대할인금액은 비율할인에만 적용되며 금액할인에는 적용되지 않습니다.");
        maxDiscountAmount = null;
    }

    fetch('/admin/couponPolicy', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            discountType: (discountType === '금액할인' ? 'COST' : 'RATE'),
            discountCost: discountAmount,
            minOrderCost: minOrderAmount,
            maxDiscountCost: maxDiscountAmount
        })
    }).then(response => {
        if (response.ok) {
            location.reload();
            alert("등록이 완료되었습니다.");
        } else {
            console.error("등록 중 오류가 발생했습니다.");
        }
    }).catch(error => {
        console.error("요청을 보내는 중 오류가 발생했습니다:", error);
    });
    createPolicyModal.style.display = 'none';
}

//정책 등록 취소
function createCancel() {
    var createPolicyModal = document.querySelector('.createPolicyModal');
    createPolicyModal.style.display = 'none';
}

//정책 개별 삭제
function deletePolicy(event) {
    var containerId = event.target.parentNode.parentNode.id;

    var isConfirm = confirm("해당 정책을 삭제하시겠습니까?");
    if (isConfirm) {
        fetch('/admin/couponPolicy', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                couponPolicyId: containerId
            }),
        }).then(response => {
            return response.json();
        }).then(data => {
            if (data.header.resultMessage == "SUCCESS") {
                location.reload();
                alert("정책 삭제 완료");
            } else {
                alert("정책 삭제 실패");
            }
        });
    }
}
