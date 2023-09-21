const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const AdminBtn = document.getElementById("AdminBtn");
const popupContainer = document.querySelector('.popup-container');
const closePopup = document.querySelector('.close-btn');
const btnLoginTop = document.getElementById('BtnMainLogin');
const btnProfile = document.getElementById('BtnMainProfile');
const btnLogin = document.getElementById('Login');
const closeBtn = document.getElementsByClassName('close-Btn');

if (window.location.pathname.endsWith('/user/login')) {
    registerLink.addEventListener('click', () => {
        wrapper.classList.add('active');
    });

    loginLink.addEventListener('click', () => {
        wrapper.classList.remove('active');
    });
}

//Break in code

const registerBtn = document.getElementById("Register");
const loginBtn = document.getElementById("Login");
//const baseUrl = 'http://localhost:8080/siteusers';

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
  //Register
    function createUser() {
        const userName = document.getElementById('RegUserName').value;
        const userPassword = document.getElementById('RegUserPassword').value;
        const userEmail = document.getElementById('UserEmail').value;
        const userIsAdmin = 0;

          $.ajax({
            url: "addUser",
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
                    /*popupContainer.classList.add('.active');
                    var RegisterHeader = document.getElementById("Popup-Header");
                    var RegisterPara = document.getElementById("Popup-Paragraph");

                    RegisterHeader.textContent = "Неуспешна регистрация!";
                    RegisterPara.textContent = "Моля затворете този прозорец и опитайте отново!";

                    closeBtn.onclick = () => {
                        popupContainer.classList.remove('.active');
                        RegisterHeader.textContent = "";
                        RegisterPara.textContent = "";
                    }*/
                    alert("Неуспешна регистрация!");
                    location.reload();

                }
                else {
                    /*popupContainer.classList.add('.active');
                    var RegisterHeader = document.getElementById("Popup-Header");
                    var RegisterPara = document.getElementById("Popup-Paragraph");

                    RegisterHeader.textContent = "Успешна регистрация!";
                    RegisterPara.textContent = "Моля затворете този прозорец и влезте в профила си!";

                    closeBtn.onclick = () => {
                        popupContainer.classList.remove('.active');
                        RegisterHeader.textContent = "";
                        RegisterPara.textContent = "";
                    }*/
                    $('#showAlert').show();
                    $('#btnSuccessNext').click(function() {
                        location.reload();
                    });
                    //location.reload();

                }
            },
            error: function(xhr, status, error) {
                console.error("Request failed: " + status);
                console.error("Error details: " + error);
                alert(1);
            }
          })
    
           
     }

    //Login
     function authenticateUser() {
        const userName = document.getElementById('UserName').value;
        const userPassword = document.getElementById('UserPassword').value;
        CurrentUser.UserName = userName;
        CurrentUser.UserPassword = userPassword;
        const user = {
            userName,
            userPassword
          };

          $.ajax({
            url: "authUser",
            type: "POST",
            crossDomain: true,
            data: JSON.stringify({
                username: userName,
                password: userPassword,
                email: null,
                isAdmin: 0
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                if(data == true)
                {
                    window.location="/user/home";
                }
                else {
                    $('#showAlert').show();
                    $('#btnSuccessNext').click(function() {
                        location.reload();
                    });
                }
                
            },
              error: function(xhr, status, error) {
                  console.error("Request failed: " + status);
                  console.error("Error details: " + error);
                  alert(2);
              }
          })
            
    }

    //Admin check when logging in to set visibility of Admin Page
    // function adminCheck(UserMain){
    //     $.ajax({
    //         url: "authUser",
    //         type: "POST",
    //         crossDomain: true,
    //         data: JSON.stringify({
    //             username: UserMain.UserName,
    //             password: UserMain.UserPassword,
    //             email: UserMain.UserEmail,
    //             isAdmin: UserMain.UserIsAdmin
    //         }),
    //         contentType: "application/json; charset=utf-8",
    //         dataType: "json",
    //         success: function (data) {
    //             if (data == true) {
    //                 CurrentUser.UserIsAdmin = 1;
    //                 //AdminBtn.style.display = "block";
    //             }
    //             else {
    //                 CurrentUser.UserIsAdmin = 0;
    //             }
    //         },
    //         error: function(xhr, status, error) {
    //             console.error("Request failed: " + status);
    //             console.error("Error details: " + error);
    //             alert(3);
    //         }
    //
    //     })
    // }

    //Admin Delete
async function deleteChosenUser(userNameDelete) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "deleteUser",
            type: "DELETE",
            crossDomain: true,
            data: JSON.stringify({
                username: userNameDelete
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
                if (data === true) {
                    resolve(true);
                } else {
                    resolve(false);
                }
            },
            error: function(xhr, status, error) {
                console.error("Request failed: " + status);
                console.error("Error details: " + error);
                alert(4);
            }
        });
    });
}

async function makeUserAdmin(userNameAdmin) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "makeAdmin",
            type: "POST",
            crossDomain: true,
            data: JSON.stringify({
                username: userNameAdmin,
                isAdmin: 1
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
                if (data === true) {
                    resolve(true);
                } else {
                    resolve(false);
                }
            },
            error: function(xhr, status, error) {
                console.error("Request failed: " + status);
                console.error("Error details: " + error);
                alert(4);
            }
        });
    });
}
if (window.location.pathname.endsWith('/user/login')) {
    registerBtn.addEventListener("click", () => {
        createUser();
    });
    loginBtn.addEventListener("click", () => {
        authenticateUser();
    });
}

let userData =[

];

document.addEventListener('DOMContentLoaded', function () {
    if (window.location.pathname.endsWith('/user/admin')) {
        function getUsers() {
            console.log("Inside getUsers");
            $.ajax({
                url: "getUsers",
                type: "GET",
                crossDomain: true,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    console.log("AJAX success:", data);
                    if (Array.isArray(data)) {
                        const tableBody = document.getElementById('userTable');
                        userData = data;

                        userData.forEach(userData => {
                            console.log("AJAX success:", userData.username);
                           let row = `
                                <tr>
                                    <td colspan="3" class="CurrentUser">${userData.username}</td>
                                    <td>
                                        <button class="change-button" id="deleteUser" onclick="handleDelete('${userData.username}')">Delete</button>
                                        <button class="change-button" id="makeAdmin" onclick="handleMakeAdmin('${userData.username}')">Make Admin</button>
                                    </td>
                                </tr>
                            `;
                            if (row.length>0) {
                                tableBody.innerHTML=tableBody.innerHTML + row;
                            }
                        });
                    }
                },
                error: function (xhr, status, error) {
                    console.error("Request failed: " + status);
                    console.error("Error details: " + error);
                    alert(4);
                }
            });
        }

        getUsers();
    }
});
async function handleDelete(username) {
    try {
        const result = await deleteChosenUser(username);
        if (result === true) {
            alert(`User '${username}' has been deleted.`);
        } else {
            alert(`Successfully deleted user '${username}'.`);
        }
    } catch (error) {
        console.error("Error deleting user:", error);
        alert(`Error deleting user '${username}': ${error.message}`);
    }
}

async function handleMakeAdmin(username) {
    try {
        const result = await makeUserAdmin(username);
        if (result === true) {
            // User was successfully made admin
            // Update the table or perform any other action
            alert(`User '${username}' has been made admin.`);
        } else {
            // Making user admin failed or returned false
            alert(`Failed to make user '${username}' admin.`);
        }
    } catch (error) {
        console.error("Error making user admin:", error);
        alert(`Error making user '${username}' admin: ${error.message}`);
    }
}

