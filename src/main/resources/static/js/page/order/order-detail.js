document.addEventListener("DOMContentLoaded", function () {
    document.querySelector('.confirm-delete').addEventListener('click', function () {
        const orderId = window.location.pathname.split('/').pop();
        console.log('orderId:', orderId)
        fetch(`/orders/${orderId}`, {
            method: 'DELETE'
        }).then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('주문 취소 요청에서 에러가 발생했습니다.');
        }).then(response => {
            console.log('Success:', response);
            $('#orderCancel').modal('hide');
            window.location.reload();
        }).catch(error => {
            console.error('Error:', error);
        });
    });
});
