document.addEventListener('DOMContentLoaded', function () {
    const orders = document.querySelectorAll('.order-detail');

    // 주문 내역 클릭
    orders.forEach(row => {
        row.addEventListener('click', function () {
            const orderId = this.getAttribute('data-order-id');
            window.location.href = `/orders/${orderId}`;
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
