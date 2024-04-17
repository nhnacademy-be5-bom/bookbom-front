document.addEventListener("DOMContentLoaded", function () {
    const searchForm = document.getElementById("search-form");
    searchForm.addEventListener("submit", function (event) {
        let input = searchForm.querySelector('input[name="keyword"]');

        if (input.value.trim().length === 0) {
            event.preventDefault();
            alert("검색할 도서를 입력하세요.");
        }
    });
});
