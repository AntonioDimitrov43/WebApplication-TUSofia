const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const AdminBtn = document.getElementById("AdminBtn");
const popupContainer = document.querySelector('.popup-container');
const closePopup = document.querySelector('.close-btn'); 
const btnProfile = document.getElementById('BtnMainProfile');
const btnLogin = document.querySelector('.LoginButton');


//AdminBtn.style.display = "none";

registerLink.addEventListener('click', ()=> {
    wrapper.classList.add('active');
});

loginLink.addEventListener('click', ()=> {
    wrapper.classList.remove('active');
});

//Break in code

const registerBtn = document.getElementById("Register");
const loginBtn = document.getElementById("Login");
const baseUrl = 'http://localhost:8080/siteusers';

const UserName = null;
const UserPassword = null;
const UserEmail = null;
const UserIsAdmin = 0;

const CurrentUser = {
    UserName,
    UserPassword,
    UserEmail,
    UserIsAdmin
  };
btnProfile.style.display = "none";
  //Register
    function createUser() {
        const userName = document.getElementById('UserName').value;
        const userPassword = document.getElementById('UserPassword').value;
        const userEmail = document.getElementById('UserEmail').value;
        const userIsAdmin = 0;

          $.ajax({
            url: "http://localhost:8080/siteusers/addUser",
            type: "POST",
            crossDomain: true,
            data: JSON.stringify({
                username: userName,
                password: userPassword,
                email: userEmail,
                isAdmin: userIsAdmin
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                if(data == false)
                {
                    popupContainer.classList.add('.active');
                    var RegisterHeader = document.getElementById("Popup-Header");
                    var RegisterPara = document.getElementById("Popup-Paragraph");

                    RegisterHeader.textContent = "Неуспешна регистрация!";
                    RegisterPara.textContent = "Моля затворете този прозорец и опитайте отново!";

                    closeBtn.onclick = () => {
                        popupContainer.classList.remove('.active');
                        RegisterHeader.textContent = "";
                        RegisterPara.textContent = "";
                    }
                }
                else {
                    popupContainer.classList.add('.active');
                    var RegisterHeader = document.getElementById("Popup-Header");
                    var RegisterPara = document.getElementById("Popup-Paragraph");

                    RegisterHeader.textContent = "Успешна регистрация!";
                    RegisterPara.textContent = "Моля затворете този прозорец и влезте в профила си!";

                    closeBtn.onclick = () => {
                        popupContainer.classList.remove('.active');
                        RegisterHeader.textContent = "";
                        RegisterPara.textContent = "";
                    }
                }
            },
            error: function(error){
                console.error("Request failed:" + error.statusText);
            }
          })

            
       /* axios.post(`http://localhost:8080/api/siteusers/addUser`, user)
             .then(response => {
                if(response.data){
                    
                    popupContainer.classList.add('.active');
                    var RegisterHeader = document.getElementById("Popup-Header");
                    var RegisterPara = document.getElementById("Popup-Paragraph");

                    RegisterHeader.textContent = "Успешна регистрация!";
                    RegisterPara.textContent = "Моля затворете този прозорец и влезте в профила си!";

                    closeBtn.onclick = () => {
                        popupContainer.classList.remove('.active');
                        RegisterHeader.textContent = "";
                        RegisterPara.textContent = "";
                    }
                }
                else{
                    popupContainer.classList.add('.active');
                    var RegisterHeader = document.getElementById("Popup-Header");
                    var RegisterPara = document.getElementById("Popup-Paragraph");

                    RegisterHeader.textContent = "Неуспешна регистрация!";
                    RegisterPara.textContent = "Моля затворете този прозорец и опитайте отново!";

                    closeBtn.onclick = () => {
                        popupContainer.classList.remove('.active');
                        RegisterHeader.textContent = "";
                        RegisterPara.textContent = "";
                    }
                }
                
             })
             .catch(error => {
                 console.error(error);
                 alert('Creation failed.');
              });*/
    
           
     }

    //Login
     function authenticateUser() {
        const userName = document.getElementById('UserName').value;
        const userPassword = document.getElementById('UserPassword').value;

        const user = {
            userName,
            userPassword
          };

          $.ajax({
            url: "http://localhost:8080/siteusers/addUser",
            type: "POST",
            crossDomain: true,
            data: JSON.stringify({
                username: userName,
                password: userPassword
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                if(data == true)
                {
                    CurrentUser.UserName=userName;
                    btnLogin.style.display = "none";
                    adminCheck(user);
                    btnProfile.style.display = "block";
                    //Change Login Button to Profile Button
                }
                else {
                    popupContainer.classList.add('.active');
                    var RegisterHeader = document.getElementById("Popup-Header");
                    var RegisterPara = document.getElementById("Popup-Paragraph");

                    RegisterHeader.textContent = "Неуспешно влизане в профила!";
                    RegisterPara.textContent = "Моля затворете този прозорец и опитайте отново!";

                    closeBtn.onclick = () => {
                        popupContainer.classList.remove('.active');
                        RegisterHeader.textContent = "";
                        RegisterPara.textContent = "";
                    }
                }
                
            },
            error: function(error){
                console.error("Request failed:" + error.statusText);
            }
          })
            
    }

    //Admin check when logging in to set visibility of Admin Page
    function adminCheck(user){
        axios.post(`${baseUrl}`,user)
        .then(response => {
            if(response.data){
                CurrentUser.UserIsAdmin = 1;
                //AdminBtn.style.display = "block";
            }
        })
    } 

    //Admin Delete
    function deleteUser() {
        $.ajax({
            url: "http://localhost:8080/siteusers/addUser",
            type: "POST",
            crossDomain: true,
            data: JSON.stringify({
                username: userName,
                password: userPassword
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                if(data == true)
                {
                
                    //Change button at the top and change reference to a Profile Page
                }
                else{
                    popupContainer.classList.add('.active');
                    var RegisterHeader = document.getElementById("Popup-Header");
                    var RegisterPara = document.getElementById("Popup-Paragraph");

                    RegisterHeader.textContent = "Неуспешно изтриване на профила!";
                    RegisterPara.textContent = "Моля затворете този прозорец и опитайте отново!";

                    closeBtn.onclick = () => {
                        popupContainer.classList.remove('.active');
                        RegisterHeader.textContent = "";
                        RegisterPara.textContent = "";
                    }
                }
            },
            error: function(error){
                console.error("Request failed:" + error.statusText);
            }
        })
    }

registerBtn.addEventListener("click", () =>{
    createUser();
});
loginBtn.addEventListener("click", () =>{
    authenticateUser();
});

function displayUsers() {

    axios.get(`${baseUrl}/display`)
        .then(response => {
            const users = response.data;

            const tableBody = document.getElementById('userTableBody');

            users.forEach(user => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>
                        <button onclick="deleteUser(${user.username})">Delete</button>
                        <button onclick="makeAdmin(${user.username})">Make Admin</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error(error);
            alert('Failed to fetch user data.');
        });
}