var categories = [
    {
        'id': 1,
        'title': 'Categoria 1'
    },
    {
        'id': 2,
        'title': 'Categoria 2'
    },
    {
        'id': 3,
        'title': 'Categoria 3'
    },
    {
        'id': 4,
        'title': 'Categoria 4'
    }
];

var taskStatus = [
    {
        'id': 1,
        'title': 'TODO'
    },
    {
        'id': 2,
        'title': 'DOING'
    },
    {
        'id': 3,
        'title': 'DONE'
    }
];

var priority = [
    {
        id: 1,
        title: 'URGENT'
    },
    {
        id: 2,
        title: 'HIGH'
    },
    {
        id: 3,
        title: 'MEDIUM'
    },
    {
        id: 4,
        title: 'MINOR'
    },
    {
        id: 5,
        title: 'LOW'
    }
];

var tasks = [
    {
        'id': 1,
        'title': 'Tarefa 1',
        'description': 'Descrição 1',
        'status': taskStatus[0].title,
        'category': categories[0].title,
        'priority': priority[1].title,
        'endDate': '2023-09-18'
    },
    {
        'id': 2,
        'title': 'Tarefa 2',
        'description': 'Descrição 2',
        'status': taskStatus[1].title,
        'category': categories[3].title,
        'priority': priority[4].title,
        'endDate': '2023-10-23'
    },
    {
        'id': 3,
        'title': 'Tarefa 3',
        'description': 'Descrição 3',
        'status': taskStatus[2].title,
        'category': categories[1].title,
        'priority': priority[0].title,
        'endDate': '2024-03-02'
    }
];

document.getElementById("navbar-options").onclick = function(event) {
    var option = event.target.innerHTML;
    switch (option) {
        case 'Tarefas':
            tasksTable();
            break;
        case 'Categorias':
            categoriesTable();
            break;
        case 'add':
            addForm();
            break;
    }
};

function add(array, obj) {
    array.push(obj);
    return array;
};

function deleteById(array, id) {
    return array.filter(function(obj) {
        return obj.id !== id;
    });
};

function dateFormat(date) {
    let dateFormatted = new Date(date);
    day = (dateFormatted.getDate() + 1).toString().padStart(2, '0');
    month = (dateFormatted.getMonth() + 1).toString().padStart(2, '0');
    year = dateFormatted.getFullYear();
    return `${day}/${month}/${year}`;
};

function addForm() {
    let container = document.getElementById("container");
    container.innerHTML = "";

    let selectField = document.createElement("ul");
    selectField.id = "form-type";

    let typeTask = document.createElement("li");
    typeTask.className = "type type-task";
    typeTask.innerHTML = "Tarefa";
    selectField.appendChild(typeTask);

    let typeCategory = document.createElement("li");
    typeCategory.className = "type type-category";
    typeCategory.innerHTML = "Categoria";
    selectField.appendChild(typeCategory);

    container.appendChild(selectField);
    let taskForm = createTaskForm();
    container.appendChild(taskForm);
    
    typeTask.addEventListener("click", function() {
        let node = document.getElementById("table");
        node.parentNode.removeChild(node);
        let taskForm = createTaskForm();
        container.appendChild(taskForm);
    });

    typeCategory.addEventListener("click", function() {
        let node = document.getElementById("table");
        node.parentNode.removeChild(node);
        let categoryForm = createCategoryForm();
        container.appendChild(categoryForm);
    });
};

function tasksTable() {
    let container = document.getElementById("container");
    container.innerHTML = "";
    let table = document.createElement("table");
    table.id = "table";
    let thead = document.createElement("thead");
    thead.innerHTML = `<th>Id</th><th>Título</th><th>Descrição</th><th>Status</th><th>Categoria</th><th>Prioridade</th><th>Prazo</th><th>Opções</th>`;
    let tbody = document.createElement("tbody");

    tasks.forEach(t => {
        let task = createTaskRow(t.id, t.title, t.description, t.status, t.category, t.priority, t.endDate);
        tbody.appendChild(task);
    });

    container.appendChild(table);
    table.appendChild(thead);
    table.appendChild(tbody);
};

function createTaskRow(id, title, description, status, category, priority, endDate) {
    let taskRow = document.createElement("tr");
    let formattedDate = dateFormat(endDate);
    taskRow.innerHTML = `
        <td>${id}</td>
        <td>${title}</td>
        <td>${description}</td>
        <td>${status}</td>
        <td>${category}</td>
        <td>${priority}</td>
        <td>${formattedDate}</td>
    `;

    let buttons = document.createElement("td");
    let editButton = document.createElement("span");
    editButton.className = "material-symbols-outlined";
    editButton.id = "edit";
    editButton.innerHTML = "edit";
    editButton.addEventListener("click", function(){
        let task = tasks.find(function(obj){
            return obj.id === id;
        });
        updateTaskForm(task);
    });

    let deleteButton = document.createElement("span");
    deleteButton.className = "material-symbols-outlined";
    deleteButton.id = "delete";
    deleteButton.innerHTML = "delete";
    deleteButton.addEventListener("click", function() {
        tasks = deleteById(tasks, id);
        tasksTable();
    });

    buttons.appendChild(editButton);
    buttons.appendChild(deleteButton);
    taskRow.appendChild(buttons);
    return taskRow;
};

function createTaskForm() {
    let form = document.createElement("form");
    form.id = "table";

    let formTextTitle = document.createElement("div");
    formTextTitle.className = "form-field form-text";
    let labelTitle = document.createElement("label");
    labelTitle.innerHTML = "Título:";
    let inputTitle = document.createElement("input");
    inputTitle.type = "text";
    inputTitle.name = "title";
    formTextTitle.appendChild(labelTitle);
    formTextTitle.appendChild(inputTitle);
    form.appendChild(formTextTitle);

    let formTextDescription = document.createElement("div");
    formTextDescription.className = "form-field form-text";
    let labelDescription = document.createElement("label");
    labelDescription.innerHTML = "Descrição:";
    let inputDescription = document.createElement("input");
    inputDescription.type = "text";
    inputDescription.name = "description";
    formTextDescription.appendChild(labelDescription);
    formTextDescription.appendChild(inputDescription);
    form.appendChild(formTextDescription);
    
    let formStatusCategory = document.createElement("div");
    formStatusCategory.classList = "form-list";
    let formTextStatus = document.createElement("div");
    formTextStatus.className = "form-field";

    let labelStatus = document.createElement("label");
    labelStatus.innerHTML = "Status:";
    formTextStatus.appendChild(labelStatus);
    let selectStatus = document.createElement("select");
    selectStatus.name = "status";
    formTextStatus.appendChild(selectStatus);
    taskStatus.forEach(s => {
        let status = document.createElement("option");
        status.value=`${s.title}`;
        status.innerHTML = `${s.title}`;
        selectStatus.appendChild(status);
    });

    let formTextCategory = document.createElement("div");
    formTextCategory.className = "form-field";
    let labelCategory = document.createElement("label");
    labelCategory.innerHTML = "Categoria:";
    formTextCategory.appendChild(labelCategory);
    let selectCategory = document.createElement("select");
    selectCategory.name = "category";
    formTextCategory.appendChild(selectCategory);
    categories.forEach(c => {
        let category = document.createElement("option");
        category.value=`${c.title}`;
        category.innerHTML = `${c.title}`;
        selectCategory.appendChild(category);
    });

    formStatusCategory.appendChild(formTextStatus);
    formStatusCategory.appendChild(formTextCategory);
    form.appendChild(formStatusCategory);
    
    let formPriorityEndDate = document.createElement("div");
    formPriorityEndDate.classList = "form-list";

    let formTextPriority = document.createElement("div");
    formTextPriority.className = "form-field";
    let labelPriority = document.createElement("label");
    labelPriority.innerHTML = "Prioridade:";
    let selectPriority = document.createElement("select");
    selectStatus.name = "priority";
    priority.forEach(p => {
        let priority = document.createElement("option");
        priority.value = `${p.title}`;
        priority.innerHTML = `${p.title}`;
        selectPriority.appendChild(priority);
    });

    let formTextEndDate = document.createElement("div");
    formTextEndDate.className = "form-field";
    let labelEndDate = document.createElement("label");
    labelEndDate.innerHTML = "Prazo:";
    let inputEndDate = document.createElement("input");
    inputEndDate.type = "date";
    inputEndDate.id = "date-input";
    inputEndDate.name = "endDate";
    formTextPriority.appendChild(labelPriority);
    formTextPriority.appendChild(selectPriority);
    formTextEndDate.appendChild(labelEndDate);
    formTextEndDate.appendChild(inputEndDate);
    formPriorityEndDate.appendChild(formTextPriority);
    formPriorityEndDate.appendChild(formTextEndDate);
    form.appendChild(formPriorityEndDate);

    let submitInput = document.createElement("input");
    submitInput.type = "submit";
    submitInput.id = "submit-input";
    submitInput.value = "Enviar";
    form.appendChild(submitInput);    
    
    submitInput.addEventListener("click", function() {
        let id = tasks.at(-1).id + 1;
        let title = inputTitle.value;
        let description = inputDescription.value;
        let status = selectStatus.value;
        let category = selectCategory.value;
        let priority = selectPriority.value;
        let endDate = inputEndDate.value;
        let task = 
        {
            'id': id,
            'title': title,
            'description': description,
            'status': status,
            'category': category,
            'priority': priority,
            'endDate': endDate
        };
        tasks = add(tasks, task);
        tasksTable();
    });    

    return form;
};

function updateTaskForm(task) {
    let container = document.getElementById("container");
    container.innerHTML = "";

    let form = document.createElement("form");
    form.id = "table";

    let formTextTitle = document.createElement("div");
    formTextTitle.className = "form-field form-text";
    let labelTitle = document.createElement("label");
    labelTitle.innerHTML = "Título:";
    let inputTitle = document.createElement("input");
    inputTitle.type = "text";
    inputTitle.name = "title";
    inputTitle.value = task.title;
    formTextTitle.appendChild(labelTitle);
    formTextTitle.appendChild(inputTitle);
    form.appendChild(formTextTitle);

    let formTextDescription = document.createElement("div");
    formTextDescription.className = "form-field form-text";
    let labelDescription = document.createElement("label");
    labelDescription.innerHTML = "Descrição:";
    let inputDescription = document.createElement("input");
    inputDescription.type = "text";
    inputDescription.name = "description";
    inputDescription.value = task.description;
    formTextDescription.appendChild(labelDescription);
    formTextDescription.appendChild(inputDescription);
    form.appendChild(formTextDescription);
    
    let formStatusCategory = document.createElement("div");
    formStatusCategory.classList = "form-list";
    let formTextStatus = document.createElement("div");
    formTextStatus.className = "form-field";
    let labelStatus = document.createElement("label");
    labelStatus.innerHTML = "Status:";
    formTextStatus.appendChild(labelStatus);
    let selectStatus = document.createElement("select");
    selectStatus.name = "status";
    formTextStatus.appendChild(selectStatus);
    taskStatus.forEach(s => {
        let status = document.createElement("option");
        status.value=`${s.title}`;
        status.innerHTML = `${s.title}`;
        selectStatus.appendChild(status);
    });
    selectStatus.value = task.status;
    formStatusCategory.appendChild(formTextStatus);

    let formTextCategory = document.createElement("div");
    formTextCategory.className = "form-field";
    let labelCategory = document.createElement("label");
    labelCategory.innerHTML = "Categoria:";
    formTextCategory.appendChild(labelCategory);
    let selectCategory = document.createElement("select");
    selectCategory.name = "category";
    formTextCategory.appendChild(selectCategory);
    categories.forEach(c => {
        let category = document.createElement("option");
        category.value=`${c.title}`;
        category.innerHTML = `${c.title}`;
        selectCategory.appendChild(category);
    });
    selectCategory.value = task.category;
    formStatusCategory.appendChild(formTextCategory);

    form.appendChild(formStatusCategory);
    
    let formPriorityEndDate = document.createElement("div");
    formPriorityEndDate.classList = "form-list";
    let formTextPriority = document.createElement("div");
    formTextPriority.className = "form-field";
    let labelPriority = document.createElement("label");
    labelPriority.innerHTML = "Prioridade:";
    let selectPriority = document.createElement("select");
    selectStatus.name = "priority";
    formTextPriority.appendChild(labelPriority);
    formTextPriority.appendChild(selectPriority);
    priority.forEach(p => {
        let priority = document.createElement("option");
        priority.value = `${p.title}`;
        priority.innerHTML = `${p.title}`;
        selectPriority.appendChild(priority);
    });
    selectPriority.value = task.priority;
    formPriorityEndDate.appendChild(formTextPriority);

    let formTextEndDate = document.createElement("div");
    formTextEndDate.className = "form-field";
    let labelEndDate = document.createElement("label");
    labelEndDate.innerHTML = "Prazo:";
    let inputEndDate = document.createElement("input");
    inputEndDate.type = "date";
    inputEndDate.id = "date-input";
    inputEndDate.name = "endDate";
    inputEndDate.value = task.endDate;
    formTextEndDate.appendChild(labelEndDate);
    formTextEndDate.appendChild(inputEndDate);
    formPriorityEndDate.appendChild(formTextEndDate);
    form.appendChild(formPriorityEndDate);

    container.appendChild(form);

    let buttonsDiv = document.createElement("div");
    buttonsDiv.id = "buttonsDiv";

    let submitInput = document.createElement("input");
    submitInput.type = "submit";
    submitInput.id = "submit-input";
    submitInput.value = "Atualizar";
    form.appendChild(submitInput);
    
    submitInput.addEventListener("click", function() {
        task.title = inputTitle.value;
        task.description = inputDescription.value;
        task.status = selectStatus.value;
        task.category = selectCategory.value;
        task.priority = selectPriority.value;
        task.endDate = inputEndDate.value;
        tasksTable();
    });
    
    let cancelInput = document.createElement("input");
    cancelInput.type = "submit";
    cancelInput.id = "cancel-input";
    cancelInput.value = "Cancelar";
    form.appendChild(cancelInput);

    buttonsDiv.appendChild(submitInput);
    buttonsDiv.appendChild(cancelInput);
    form.appendChild(buttonsDiv);
    
    cancelInput.addEventListener("click", function() {
        tasksTable();
    });
};

function categoriesTable() {
    let container = document.getElementById("container");
    container.innerHTML = "";

    let table = document.createElement("table");
    table.id = "table";
    
    let thead = document.createElement("thead");
    thead.innerHTML = `<th>Id</th><th>Título</th><th>Opções</th>`;

    let tbody = document.createElement("tbody");

    categories.forEach(c => {
        let category = createCategoryRow(c.id, c.title);
        tbody.appendChild(category);
    });

    container.appendChild(table);
    table.appendChild(thead);
    table.appendChild(tbody);
};

function createCategoryRow(id, title) {
    let categoryRow = document.createElement("tr");
    categoryRow.innerHTML = `
        <td>${id}</td>
        <td>${title}</td>
    `;

    let buttons = document.createElement("td");

    let editButton = document.createElement("span");
    editButton.className = "material-symbols-outlined";
    editButton.id = "edit";
    editButton.innerHTML = "edit";

    editButton.addEventListener("click", function(){
        let category = categories.find(function(obj){
            return obj.id === id;
        });
        updateCategoryForm(category);
    });

    let deleteButton = document.createElement("span");
    deleteButton.className = "material-symbols-outlined";
    deleteButton.id = "delete";
    deleteButton.innerHTML = "delete";

    deleteButton.addEventListener("click", function() {
        categories = deleteById(categories, id);
        categoriesTable();
    });

    buttons.appendChild(editButton);
    buttons.appendChild(deleteButton);

    categoryRow.appendChild(buttons);
    return categoryRow;
};

function createCategoryForm() {
    let form = document.createElement("form");
    form.id = "table";

    let formTextTitle = document.createElement("div");
    formTextTitle.className = "form-field form-text";
    let labelTitle = document.createElement("label");
    labelTitle.innerHTML = "Título:";
    let inputTitle = document.createElement("input");
    inputTitle.type = "text";
    inputTitle.name = "title";
    formTextTitle.appendChild(labelTitle);
    formTextTitle.appendChild(inputTitle);
    form.appendChild(formTextTitle);

    let submitInput = document.createElement("input");
    submitInput.type = "submit";
    submitInput.id = "submit-input";
    submitInput.value = "Enviar";
    form.appendChild(submitInput);
    
    submitInput.addEventListener("click", function() {
        let id = categories.at(-1).id + 1;
        let title = inputTitle.value;
        let category = { 'id': id, 'title': title};
        categories = add(categories, category);
        categoriesTable();
    });

    return form;
};

function updateCategoryForm(category) {
    let container = document.getElementById("container");
    container.innerHTML = "";

    let form = document.createElement("form");
    form.id = "table";

    let formTextTitle = document.createElement("div");
    formTextTitle.className = "form-field form-text";
    let labelTitle = document.createElement("label");
    labelTitle.innerHTML = "Título:";

    let inputTitle = document.createElement("input");
    inputTitle.type = "text";
    inputTitle.name = "title";
    inputTitle.value = category.title;

    formTextTitle.appendChild(labelTitle);
    formTextTitle.appendChild(inputTitle);
    form.appendChild(formTextTitle);

    container.appendChild(form);

    let buttonsDiv = document.createElement("div");
    buttonsDiv.id = "buttonsDiv";

    let submitInput = document.createElement("input");
    submitInput.type = "submit";
    submitInput.id = "submit-input";
    submitInput.value = "Atualizar";
    form.appendChild(submitInput);
    
    submitInput.addEventListener("click", function() {
        let title = inputTitle.value;
        category.title = title;
        categoriesTable();
    });

    let cancelInput = document.createElement("input");
    cancelInput.type = "submit";
    cancelInput.id = "cancel-input";
    cancelInput.value = "Cancelar";

    buttonsDiv.appendChild(submitInput);
    buttonsDiv.appendChild(cancelInput);
    form.appendChild(buttonsDiv);
    
    cancelInput.addEventListener("click", function() {
        categoriesTable();
    });
};
