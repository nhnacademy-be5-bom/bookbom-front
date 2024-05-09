function selected(e) {
    var selectedOption = e.value;
    if (selectedOption == 'book') {
        location.href = "/admin/coupons/info/book";
    } else if (selectedOption == 'category') {
        location.href = "/admin/coupons/info/category";
    } else if (selectedOption == 'general') {
        location.href = "/admin/coupons/info/general";
    }
}
