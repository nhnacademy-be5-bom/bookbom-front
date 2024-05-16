function validateForm() {
    const orderId = document.getElementById("orderId").value;
    const cancelReason = document.getElementById("cancelReason").value;
    console.log(orderId);
    console.log(cancelReason)
// POST 요청을 보낼 URL을 지정합니다.
    const url = '/cancel' + '?id=' + orderId + '&reason=' + cancelReason;
    console.log(url);
    if (cancelReason.trim() === "") {
        alert("취소 이유를 입력해주세요.");
        return false;
    } else {
        fetch(url).then(response => response.json())
            .then(data => {
                if (data.header.successful) {
                    alert('주문 취소가 완료되었습니다.');
                    window.close();
                } else {
                    alert('주문 취소가 실패했습니다. 관리자에게 문의하세요.');
                }
            }).catch(error => {
            console.error('Error:', error);
        });
        return true;
    }

}
