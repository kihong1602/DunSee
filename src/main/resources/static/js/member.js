const SUCCESS = "SUCCESS"
const FAILURE = "FAILURE"

function loginProcess() {
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

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

  })
}

function registerProcess() {
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;
  const email = document.getElementById('email').value;

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
    switch (jsonData.data.value) {
      case SUCCESS: {
        alert("회원가입이 완료되었습니다. 로그인페이지로 이동합니다.")
        location.href = "/login"
        break
      }
      case FAILURE : {
        alert("회원가입에 실패하였습니다. 다시한번 확인해주세요.")
        break
      }
    }
  })
}

function setJoinUserData(username, password, email) {
  return {username: username, password: password, email: email}
}

function setLoginUserData(username, password) {
  return {username: username, password: password}
}