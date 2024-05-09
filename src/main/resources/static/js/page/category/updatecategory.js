async function setChildCategory(selectBox, categoryId) {
    const currentDepth = Number(selectBox.id.slice(-1));

    const response = await fetch(
        GATEWAY_URL + "/shop/category/" + categoryId,
        {
            method: "GET"
        });

    const childList = await response.json();

    while (selectBox && selectBox.nextSibling) {
        selectBox.parentNode.removeChild(selectBox.nextSibling)
    }

    if (childList.result.length !== 0) {
        const nextDepthSelectBox = createCategorySelectBox(currentDepth + 1);

        for (const child of childList.result) {
            const newCategory = document.createElement('option');
            newCategory.className = child.id;
            newCategory.text = child.name;

            nextDepthSelectBox.appendChild(newCategory);
        }
        selectBox.parentNode.appendChild(nextDepthSelectBox);
    }

}

function createCategorySelectBox(depth) {
    const newSelect = document.createElement('select');
    newSelect.className = 'form-control';
    newSelect.id = 'category_depth' + depth;
    newSelect.name = 'categories';
    newSelect.setAttribute('onchange', 'setChildCategory(this, this.options[this.selectedIndex].className)');

    const newCategory = document.createElement('option');
    newCategory.className = '0';
    newCategory.value = 'none';
    newCategory.disabled = true;
    newCategory.selected = true;
    newCategory.text = '카테고리 선택';

    newSelect.appendChild(newCategory);

    return newSelect;
}

