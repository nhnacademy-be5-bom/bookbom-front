var checkboxes = document.querySelectorAll('input[type="checkbox"]');
checkboxes.forEach(function (checkbox) {
    checkbox.addEventListener('change', function () {
        checkboxes.forEach(function (cb) {
            if (cb !== checkbox) {
                cb.checked = false;
            } else {
                sendSelectedZipCodesToParent();
            }

        });
    });
});


function sendSelectedZipCodesToParent() {
    // 선택된 체크박스들의 정보를 저장할 배열

    // 주소록 항목들을 탐색하며 선택된 체크박스들의 정보를 가져옴
    var selectedRow = document.querySelector('input[type="checkbox"]:checked').parentNode.parentNode;
    var selectedZipCode = selectedRow.querySelector('.child-zipcode').innerText;
    var selectedAddress = selectedRow.querySelector('.child-address').innerText;
    var selectedAddressDetail = selectedRow.querySelector('.child-addressDetail').innerText;

    // 부모 창의 함수 호출하여 선택된 주소 정보 전달
    window.opener.receiveSelectedAddress(selectedZipCode, selectedAddress, selectedAddressDetail);


}
