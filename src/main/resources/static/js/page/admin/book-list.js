/* global bootstrap: false */
(() => {
    'use strict'
    const tooltipTriggerList = Array.from(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    tooltipTriggerList.forEach(tooltipTriggerEl => {
        new bootstrap.Tooltip(tooltipTriggerEl)
    })
})()

document.addEventListener('DOMContentLoaded', function () {
    // 페이지 크기 선택
    let pageSizeParam = new URLSearchParams(window.location.search).get('size');
    let pageSizeSelect = document.querySelector('.page-size');
    if (!pageSizeParam) {
        pageSizeSelect.value = "5";
    } else {
        pageSizeSelect.value = pageSizeParam;
    }
    pageSizeSelect.addEventListener('change', function () {
        let selectedPageSize = this.value;
        let currentUrl = new URL(window.location.href);
        currentUrl.searchParams.delete("sorted");
        currentUrl.searchParams.delete("page");
        currentUrl.searchParams.set('size', selectedPageSize);
        window.location.replace(currentUrl.toString());
    });
});
