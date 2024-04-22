
// 검색 기능 구현
function searchContent() {
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById('searchInput');
    filter = input.value.toUpperCase();
    ul = document.getElementsByTagName('ul')[0]; // 첫 번째 ul 요소 선택
    li = ul.getElementsByTagName('li');

    // 각 li 요소에 대해 검색어와 일치하는지 확인
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName('a')[0];
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = '';
        } else {
            li[i].style.display = 'none';
        }
    }
}

