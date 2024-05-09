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

    fetch('/admin/couponPolicy', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            couponPolicyId: couponPolicyId,
            discountType: discountType,
            discountCost: discountAmount,
            minOrderCost: minOrderAmount,
            maxDiscountCost: maxDiscountAmount
        })
    }).then(response => {
        if (response.ok) {
            alert("수정이 완료되었습니다.");
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
                    alert("정책 삭제 실패");
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

    fetch('/admin/couponPolicy', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            discountType: discountType,
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
