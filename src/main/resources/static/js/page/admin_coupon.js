document.addEventListener("DOMContentLoaded", function () {
    // 수정 버튼 클릭 시 필드 활성화/비활성화 및 버튼 텍스트 변경
    var editButtons = document.querySelectorAll('.policyButtonContainer .editBtn');
    editButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            toggleFields(button);
        });
    });

    // 선택 삭제 버튼 클릭 시 선택된 policyContainer 삭제
    var selectDeleteButton = document.querySelector('.selectDeleteBtn');
    if (selectDeleteButton) {
        selectDeleteButton.addEventListener('click', deleteSelectedContainers);
    }

    // 개별 삭제 버튼 클릭 시 해당 policyContainer 삭제
    var deleteButtons = document.querySelectorAll('.policyButtonContainer .btn-danger');
    deleteButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            var container = button.closest('.policyContainer');
            var containerId = container.id;

            fetch('/couponPolicy', {
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
                    container.remove();
                } else {
                    alert("정책 삭제 실패");
                }
            })
            deleteContainer(button);
        });
    });
});

function toggleFields(button) {
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

function completeEditing(button) {
    var container = button.closest('.policyContainer');
    var selects = container.querySelectorAll('select');
    var inputs = container.querySelectorAll('input[type="text"]');

    var newData = {};

    selects.forEach(function (select) {
        newData[select.name] = select.value;
    });

    inputs.forEach(function (input) {
        if (input.value.trim() !== '') {
            newData[input.name] = input.value;
        }
    });

    fetch('/couponPolicy', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newData)
    }).then(response => {
        if (response.ok) {
            console.log("수정이 완료되었습니다.");
        } else {
            console.error("수정 중 오류가 발생했습니다.");
        }
    }).catch(error => {
        console.error("요청을 보내는 중 오류가 발생했습니다:", error);
    });
}

//정책 삭제
function deleteSelectedContainers() {
    var containers = document.querySelectorAll('.policyContainer');
    containers.forEach(function (container) {
        var checkbox = container.querySelector('input[type="checkbox"]');
        if (checkbox.checked) {
            var checkedId = checkbox.id;
            container.remove();
            fetch('/couponPolicy', {
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

function deleteContainer(button) {
    var container = button.closest('.policyContainer');
    container.remove();
}

document.addEventListener('DOMContentLoaded', function () {
    var createPolicyModal = document.querySelector('.createPolicyModal');
    var showModalBtn = document.querySelector('.showModalBtn');
    showModalBtn.addEventListener('click', function () {
        createPolicyModal.style.display = 'block';
    });

    // 등록 버튼 클릭 시 이벤트 처리
    var createBtn = document.querySelector('.createBtn');
    createBtn.addEventListener('click', function () {
        // 입력된 값을 가져오기
        var discountType = document.querySelector('.createPolicyModal select[name="discountType"]').value;
        var discountAmount = document.querySelector('.createPolicyModal input[name="discountAmount"]').value;
        var minOrderAmount = document.querySelector('.createPolicyModal input[name="minOrderAmount"]').value;
        var maxDiscountAmount = document.querySelector('.createPolicyModal input[name="maxDiscountAmount"]').value;


        fetch('/couponPolicy', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                discountType: discountType,
                discountAmount: discountAmount,
                minOrderAmount: minOrderAmount,
                maxDiscountAmount: maxDiscountAmount
            })
        }).then(response => {
            if (response.ok) {
                console.log("등록이 완료되었습니다.");
            } else {
                console.error("등록 중 오류가 발생했습니다.");
            }
        }).catch(error => {
            console.error("요청을 보내는 중 오류가 발생했습니다:", error);
        });

        // 등록 폼 숨기기
        createPolicyModal.style.display = 'none';
    });
});
