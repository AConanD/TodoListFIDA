function fillTodoTable() {

    console.log("fillTodoTable function-Start");

    fetch("http://localhost:8080/todos")
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Data received:', data);
            let table = document.querySelector('#todoTable');

            let tableHead = document.createElement('thead');
            let headRow = document.createElement('tr');
            let headRow1 =document.createElement('td');
            let headRow2 =document.createElement('td');
            let headRow3 =document.createElement('td');
            let headRow4 =document.createElement('td');
            let headRow5 =document.createElement('td');
            headRow1.textContent = 'Description';
            headRow2.textContent = 'Create Date';
            headRow3.textContent = 'Done Date';
            headRow4.textContent = 'Is Done';
            headRow5.textContent = 'Actions';
            headRow.appendChild(headRow1);
            headRow.appendChild(headRow2);
            headRow.appendChild(headRow3);
            headRow.appendChild(headRow4);
            headRow.appendChild(headRow5);
            tableHead.appendChild(headRow);
            table.appendChild(tableHead);


            let todos = data.content;
            console.log('Content:', data.content);
            for (let i = 0; i <todos.length; i++) {
                let tr = document.createElement('tr');

                let td1 = document.createElement('td');
                td1.textContent = todos[i].description;
                tr.appendChild(td1);
                let td2 = document.createElement('td');
                td2.textContent = todos[i].createDate;
                tr.appendChild(td2);
                let td3 = document.createElement('td');
                td3.textContent = todos[i].doneDate;
                tr.appendChild(td3);
                let td4 = document.createElement('td');
                td4.textContent = todos[i].done;
                tr.appendChild(td4);
                let td5 = document.createElement('td');

                var btn = document.createElement('input');
                btn.type = "button";
                btn.className = "btn";
                btn.value = "delete";
                btn.onclick = (function() {
                    return function () {
                        deleteEntry(todos[i].id);
                    }
                })(todos[i].id);
                td5.appendChild(btn);
                tr.appendChild(td5);

                table.appendChild(tr);
            }

        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

function deleteEntry(id) {
    console.log("Deleting Entry with ID:", id);

    const requestOptions = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        }
    };

    fetch("http://localhost:8080/todos/" + id, requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            outputElement.textContent = JSON.stringify(data, null, 2);
        })
        .catch(error => {
            console.error

            ('Error:', error);
        });
    //TODO: Page-Reload
    //location.reload();
}