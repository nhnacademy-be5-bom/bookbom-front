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