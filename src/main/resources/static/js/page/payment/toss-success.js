function confirmPayment() {
    const form = document.createElement('form');
    form.method = 'post';
    form.action = '/payment-complete';

    const orderId = document.getElementById("order-id").value;
    const paymentKey = document.getElementById("payment-key").value;
    const amount = document.getElementById("amount").value;

    const orderIdInput = document.createElement('input');
    orderIdInput.type = 'hidden';
    orderIdInput.name = "orderId";
    orderIdInput.value = orderId;
    form.appendChild(orderIdInput);

    const paymentKeyInput = document.createElement('input');
    paymentKeyInput.type = 'hidden';
    paymentKeyInput.name = "paymentKey";
    paymentKeyInput.value = paymentKey;
    form.appendChild(paymentKeyInput);

    const amountInput = document.createElement('input');
    amountInput.type = 'hidden';
    amountInput.name = "amount";
    amountInput.value = amount;
    form.appendChild(amountInput);


    document.body.appendChild(form);
    form.submit();
}
