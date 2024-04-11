function add() {
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

function setDescription() {
    const editor = document.getElementById('editor');
    const description = document.getElementById('description');

    description.value = editor.getHTML();
}

async function setChildCategory(selectBox, categoryId) {
    const depth2 = document.getElementById('category_depth2');
    const depth3 = document.getElementById('category_depth3');

    const target = selectBox.nextElementSibling;

    if (target === depth2) { // 깊이1 카테고리를 선택한 경우
        // 깊이 2,3 카테고리 초기화
        while (depth2.children.length > 1) {
            depth2.removeChild(depth2.lastChild);
        }
        while (depth3.children.length > 1) {
            depth3.removeChild(depth3.lastChild);
        }

    } else if (target === depth3) { // 깊이2 카테고리를 선택한 경우
        // 깊이3 카테고리 초기화
        while (depth3.children.length > 1) {
            depth3.removeChild(depth3.lastChild);
        }
    }

    const response = await fetch(
        "http://127.0.0.1:8880/shop/category/get/" + categoryId,
        {
            method: "GET"
        });

    const childList = await response.json();

    for (const child of childList.result) {

        const newCategory = document.createElement('option');
        newCategory.className = child.id;
        newCategory.text = child.name;

        target.appendChild(newCategory);
    }
}