document.addEventListener('DOMContentLoaded', function () {
    const orders = document.querySelectorAll('.latest-order');

    // 주문 내역 클릭
    orders.forEach(row => {
        row.addEventListener('click', function () {
            const orderId = this.getAttribute('data-order-id');
            window.location.href = `/order/${orderId}`;
        });
    });

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
