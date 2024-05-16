document.addEventListener('DOMContentLoaded', function () {
    const links = document.querySelectorAll('.member-link');
    links.forEach(item => {
        item.addEventListener('click', function () {
            const href = this.getAttribute('data-href');
            if (href) {
                window.location.href = href;
            }
        });
    });
});
