document.addEventListener("DOMContentLoaded", function () {
    // 수량 감소 버튼 클릭
    document.querySelectorAll('.quantity-decrease').forEach(button => {
        button.addEventListener('click', function () {
            const itemID = this.getAttribute('data-item');
            const input = document.querySelector(`.quantity-input[data-item="${itemID}"]`);
            // 10진수 숫자로 변환
            let currentValue = parseInt(input.value, 10);
            if (isNaN(currentValue)) {
                currentValue = 1; // currentValue가 비어있거나 NaN인 경우 1로 설정
            } else if (currentValue > 1) {
                currentValue -= 1;
            }
            input.value = currentValue;
        });
    });

    // 수량 증가 버튼 클릭
    document.querySelectorAll('.quantity-increase').forEach(button => {
        button.addEventListener('click', function () {
            const itemID = this.getAttribute('data-item');
            const input = document.querySelector(`.quantity-input[data-item="${itemID}"]`);
            let currentValue = parseInt(input.value, 10);
            if (isNaN(currentValue)) {
                currentValue = 1;
            } else {
                currentValue += 1;
                if (currentValue > 99) {
                    currentValue = 99;
                }
            }
            input.value = currentValue;
        });
    });

    // 수량 입력 필드의 값 변경
    document.querySelectorAll('.quantity-input').forEach(input => {
        // 입력 값이 바뀔 때
        input.addEventListener('input', function () {
            let currentValue = parseInt(this.value, 10);
            if (!isNaN(currentValue)) {
                currentValue = Math.max(0, currentValue);
                currentValue = Math.min(99, currentValue); // 최대값 99로 제한
                this.value = currentValue;
            }
        });
        // 입력 값 포커스가 해제될 때
        input.addEventListener('blur', function () {
            let currentValue = parseInt(this.value, 10);
            if (isNaN(currentValue)) {
                currentValue = 0; // currentValue가 비어있거나 NaN인 경우 0으로 설정
            }
            this.value = currentValue;
        });
    });


    // 체크박스
    const checkAllCheckbox = document.getElementById('checkAll');
    const itemCheckboxes = document.querySelectorAll('.custom-control-input');

    // 전체 선택 체크박스를 클릭했을 때
    checkAllCheckbox.addEventListener('change', function () {
        itemCheckboxes.forEach(function (checkbox) {
            checkbox.checked = checkAllCheckbox.checked;
        });
    });

    // 개별 상품 체크박스를 클릭했을 때의 동작
    itemCheckboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            // 모든 개별 상품 체크박스가 선택되었으면 전체 선택 체크박스 체크
            checkAllCheckbox.checked = Array.from(itemCheckboxes).every(function (checkbox) {
                return checkbox.checked;
            });
        });
    });
});