function add() {
    // 태그 컨트롤 자바스크립트
    const taginput = document.getElementById('taginput').value;

    if (taginput.length !== 0 && !findDuplicateTag(taginput)) {
        const newDiv = document.createElement('div');

        const newTag = document.createElement('input');
        newTag.setAttribute('type', 'tags');
        newTag.setAttribute('name', 'tags');
        newTag.setAttribute('class', 'tags');
        newTag.setAttribute('size', 50);
        newTag.setAttribute('readonly', true);
        newTag.value = taginput;

        const removeButton = document.createElement('button');
        removeButton.appendChild(document.createTextNode('X'))
        removeButton.setAttribute('onclick', 'removeTag(this)');

        newDiv.append(newTag, removeButton);

        document.getElementById('generatedtags').appendChild(newDiv);
        document.getElementById('taginput').value = '';
    }

}

function findDuplicateTag(inputValue) {
    const tags = document.getElementsByClassName('tags')

    for (const tag_element in tags) {
        if (tag_element.value === inputValue)
            return true;
    }

    return false;
}

function removeTag(tagElement) {
    tagElement.parentNode.remove();
}


async function setChildCategory(selectBox, categoryId) {
    const currentDepth = Number(selectBox.id.slice(-1));

    const response = await fetch(
        GATEWAY_URL + "/shop/categories" + categoryId,
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

function addAuthor() {
    const authorDiv = document.querySelector('.authorContainer0').cloneNode(true);
    const authorFormDiv = document.getElementById('authorForm');
    const addButton = document.getElementById('addAuthorButton');

    let currentAuthorIndex = Number(
        addButton.previousElementSibling.className.replace("authorContainer", ""));
    currentAuthorIndex++;

    authorDiv.className = 'authorContainer' + currentAuthorIndex;

    authorDiv.querySelector('input[name="authors[0].role"]').value = '';
    authorDiv.querySelector('input[name="authors[0].role"]').name = 'authors[' + currentAuthorIndex + '].role';

    authorDiv.querySelector('input[name="authors[0].name"]').value = '';
    authorDiv.querySelector('input[name="authors[0].name"]').name = 'authors[' + currentAuthorIndex + '].name';

    authorFormDiv.insertBefore(authorDiv, addButton);
}
