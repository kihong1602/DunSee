const SUCCESS = "SUCCESS"
const FAILURE = "FAILURE"
const EXIST = "EXIST"

function loginEnterKey(e) {
  if (e.code === 'Enter') {
    loginProcess();
  }
}

const loginContainer = document.getElementById('login-container');
loginContainer.addEventListener('keyup', e => loginEnterKey(e));

function loginProcess() {
  let username = document.getElementById('username');
  let password = document.getElementById('password');

  const data = setLoginUserData(username, password);

  fetch('/login-process', {
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
        alert("로그인에 성공하였습니다.")
        location.href = detail
        break
      }
      case FAILURE : {
        alert(detail)
        username.value = '';
        password.value = '';
        break
      }
    }
  })
}

function setLoginUserData(username, password) {
  return {username: username.value, password: password.value}
}