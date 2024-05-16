function selected(e) {
    var selectedOption = e.value;
    if (selectedOption == 'book') {
        location.href = "/admin/coupons/book";
    } else if (selectedOption == 'category') {
        location.href = "/admin/coupons/category";
    } else if (selectedOption == 'general') {
        location.href = "/admin/coupons/general";
    }
}

//쿠폰 발급 버튼 눌렀을 때 체크박스 선택된 쿠폰으로 발급요청
function issueCoupon(e){
    var query = 'input[type="checkbox"]:checked';
    var selectedElements = document.querySelectorAll(query);
    var selectedElementsCnt = selectedElements.length;

    if(selectedElementsCnt == 1){
        var selected = selectedElements.item(0);
        var couponId = selected.id;
        var couponName = selected.parentNode.parentNode.children[1].children[0].valueOf().value;
        localStorage.setItem("couponId", couponId);
        localStorage.setItem("couponName", couponName);
        location.href = "/admin/coupons/issue";
    }
    else if(selectedElementsCnt < 1){
        alert("발급할 쿠폰을 선택해주세요.");
    }else{
        alert("발급할 쿠폰 한개만 선택해주세요.");
    }
}

