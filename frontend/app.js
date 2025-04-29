const apiBaseUrl = "http://localhost:8080/api/employee";

// ADD Employee
const addForm = document.getElementById('addForm');
if (addForm) {
    addForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const employee = {
            name: document.getElementById('name').value,
            email: document.getElementById('email').value,
            role: document.getElementById('role').value,
            salary: document.getElementById('salary').value
        };

        try {
            await axios.post(`${apiBaseUrl}/add`, employee);
            alert("Employee added successfully!");
            addForm.reset();
        } catch (error) {
            alert("Failed to add employee!");
        }
    });
}

// DISPLAY Employees
const employeeTable = document.getElementById('employeeTable');
if (employeeTable) {
    axios.get(`${apiBaseUrl}/displayAll`)
        .then(response => {
            const data = response.data;
            data.forEach(emp => {
                const row = `<tr>
                    <td class="py-2 px-4 text-center">${emp.id}</td>
                    <td class="py-2 px-4 text-center">${emp.name}</td>
                    <td class="py-2 px-4 text-center">${emp.email}</td>
                    <td class="py-2 px-4 text-center">${emp.role}</td>
                    <td class="py-2 px-4 text-center">${emp.salary}</td>
                </tr>`;
                employeeTable.innerHTML += row;
            });
        })
        .catch(error => {
            console.error("Error fetching employees:", error);
            alert("Failed to load employee data.");
        });
}


// SEARCH by ID
async function searchEmployee() {
    const id = document.getElementById('searchId').value;
    try {
        const res = await axios.get(`${apiBaseUrl}/display/${id}`);
        const emp = res.data;
        document.getElementById('employeeDetails').classList.remove('hidden');
        document.getElementById('name').value = emp.name;
        document.getElementById('email').value = emp.email;
        document.getElementById('role').value = emp.role;
        document.getElementById('salary').value = emp.salary;
    } catch (error) {
        alert("Employee not found!");
    }
}

// UPDATE Employee
async function updateEmployee() {
    const id = document.getElementById('searchId').value;
    const updatedEmployee = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        role: document.getElementById('role').value,
        salary: document.getElementById('salary').value
    };

    try {
        await axios.put(`${apiBaseUrl}/update/${id}`, updatedEmployee);
        alert("Employee updated successfully!");
    } catch (error) {
        alert("Failed to update!");
    }
}

// DELETE Employee
async function deleteEmployee() {
    const id = document.getElementById('searchId').value;

    try {
        await axios.delete(`${apiBaseUrl}/delete/${id}`);
        alert("Employee deleted successfully!");
        document.getElementById('employeeDetails').classList.add('hidden');
    } catch (error) {
        alert("Failed to delete!");
    }
}
