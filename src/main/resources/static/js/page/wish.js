document.addEventListener("DOMContentLoaded", function () {
    // 전체 선택 체크박스
    const checkAllCheckbox = document.getElementById('checkAll');

    // 개별 상품 체크박스들
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
