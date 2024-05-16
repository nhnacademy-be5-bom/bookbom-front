// document.addEventListener("DOMContentLoaded", function () {
//     document.querySelector('.confirm-delete').addEventListener('click', function () {
//         const orderId = window.location.pathname.split('/').pop();
//         console.log('orderId:', orderId)
//
//     });
// });

// const orderIdValue = document.getElementById("orderId").value;
// const orderId = parseInt(orderIdValue);
document.addEventListener("DOMContentLoaded", function () {
    document.querySelector('.confirm-delete').addEventListener('click', function () {
        // 팝업 창으로 페이지 열기
        // orderId 가져오기
        const orderId = document.getElementById("orderId").value;
        // 팝업 창으로 페이지 열기
        window.open("/order/cancel?id=" + orderId, '_blank', 'width=600,height=400,toolbar=0,location=0,menubar=0,status=0,scrollbars=1,resizable=1');
    });
});
