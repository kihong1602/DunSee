const SUCCESS = "SUCCESS"
const FAILURE = "FAILURE"
const EXIST = "EXIST"

function registerEnterKey(e) {
  if (e.code === 'Enter') {
    registerProcess();
  }
}

const registerContainer = document.getElementById('register-container');
registerContainer.addEventListener('keyup', e => registerEnterKey(e));

function registerProcess() {
  let username = document.getElementById('username');
  let password = document.getElementById('password');
  let email = document.getElementById('email');

  const data = setJoinUserData(username, password, email);

  fetch('/register-process', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  .then(response => response.text())
  .then(result => {
    const jsonData = JSON.parse(result);
    console.log(jsonData.code);
    console.log(jsonData.description);
    console.log(jsonData.data.value);
    const detail = jsonData.detail
    switch (jsonData.data.value) {
      case SUCCESS: {
        alert(detail)
        location.href = "/login"
        break
      }
      case FAILURE : {
        alert(detail)
        username.value = '';
        password.value = '';
        email.value = '';
        break
      }
      case EXIST: {
        alert(detail)
        username.value = '';
        password.value = '';
        email.value = '';
        break;
      }
    }
  })
}

function setJoinUserData(username, password, email) {
  return {
    username: username.value,
    password: password.value,
    email: email.value
  }
}
