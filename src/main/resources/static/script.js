document.addEventListener('DOMContentLoaded', () => {
    const taskForm = document.getElementById('task-form');
    const taskList = document.getElementById('tasks');

    const apiUrl = 'http://localhost:8080/v17/task';

    // Fetch tasks and display them
    const fetchTasks = async () => {
        const response = await fetch(apiUrl);
        const tasks = await response.json();
        taskList.innerHTML = '';
        tasks.forEach(task => {
            const taskItem = document.createElement('li');
            taskItem.innerHTML = `
                <span>${task.title} - ${new Date(task.dueDate).toLocaleString()}</span>
                <span>
                    <button class="edit" onclick="editTask(${task.id})">Edit</button>
                    <button class="delete" onclick="deleteTask(${task.id})">Delete</button>
                </span>
            `;
            taskList.appendChild(taskItem);
        });
    };

    // Add new task
    taskForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const title = document.getElementById('title').value;
        const description = document.getElementById('description').value;
        const dueDate = document.getElementById('dueDate').value;

        await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ title, description, dueDate })
        });

        taskForm.reset();
        fetchTasks();
    });

    // Edit task
    window.editTask = async (id) => {
        const task = await fetch(`${apiUrl}/${id}`).then(response => response.json());
        document.getElementById('title').value = task.title;
        document.getElementById('description').value = task.description;
        document.getElementById('dueDate').value = new Date(task.dueDate).toISOString().slice(0, 16);

        taskForm.onsubmit = async (e) => {
            e.preventDefault();
            await fetch(`${apiUrl}/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    title: document.getElementById('title').value,
                    description: document.getElementById('description').value,
                    dueDate: document.getElementById('dueDate').value,
                    completed: task.completed
                })
            });

            taskForm.onsubmit = addTask;
            taskForm.reset();
            fetchTasks();
        };
    };

    // Delete task
    window.deleteTask = async (id) => {
        await fetch(`${apiUrl}/${id}`, {
            method: 'DELETE'
        });
        fetchTasks();
    };

    fetchTasks();
});
