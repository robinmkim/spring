<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

  <p>
    <span class="error-message"></span>
  </p>

  <p>
    login : <input type="text" id="username" name="username" class="login-package">
    <button id="loginBtn" class="login-package">login</button>
  </p>

  <p>
    <input id="textMessage" l,'L.type="text" disabled="disabled" class="chat-package">
    <button id="sendBtn" disabled="disabled" class="chat-package">Send</button>
  </p>

  <p>
    <textarea id="messageTextArea" rows="10" cols="50" disabled="disabled"></textarea>
  </p>
  <script>
  	$(function() {
	  // WebSocket ����
      let webSocket = null;
      // WebSocket ���� �Լ�
            // WebSocket ���� �Լ�
      function connect() {
        // �� ���� ��ü ����
        webSocket = new WebSocket("ws://localhost:8099/spring1025_26_27AapLogSocket/chat");
        // �� ������ open�Ǹ� ����Ǵ� �̺�Ʈ
        webSocket.onopen = function(message) {
          // ä�� textbox�� ��ư�� disabled ����
          $(".chat-package").attr("disabled", false);
          // ���� �ʱ� ������ �ۼ�
          let key = {
            id : $("#username").val(),
            state : 0 // state�� 0
          };
          // ���� �޽��� ������
          webSocket.send(JSON.stringify(key));
        };
        webSocket.onclose = function(message) {
        };
        webSocket.onerror = function(message) {
        };
        // ������ ���� �޽����� ���� ����Ǵ� �̺�Ʈ
        webSocket.onmessage = function(message) {
          console.log("Me:"+$("#username").val());	
       		// ä�� ���� �ڽ� ��ü ���
          let messageTextArea = document.getElementById("messageTextArea");
          var arr = message.data.split(":");
          if(arr[0] == $("#username").val()){
        	  console.log("���� ��� "+$("#username").val()+"Message:"+message.data);
        	// ������ �ۼ�
              messageTextArea.value += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+message.data;
          }else{
        	  console.log("�ٸ� ���"+arr[0]+"Message:"+message.data);	
        	// ������ �ۼ�
              messageTextArea.value += message.data;
          }
        };
      }

      $("#loginBtn").click(function() {
    	  // ���̵� �ؽ�Ʈ �ڽ��� ������ ������
          if ($.trim($("#username").val()) === '') {
            // ���� ǥ��
            $(".error-message").html("Please input the name textbox.");
            // �Լ� ����
            return false;
          }
          // ���� �޽��� ����
          $(".error-message").html("");
          // �α��� �ؽ�Ʈ �ڽ��� ��ư disabled
          $(".login-package").attr("disabled", "disabled");
          // ����
          connect();
        });
      // ä�� ��ư ������ �޽��� ������
      $("#sendBtn").click(function(){
    	// �ؽ�Ʈ �ڽ� ��ü ���
          let message = document.getElementById("textMessage");
          // �޽��� ���� ��ü �����
          let key = {
            id : $("#username").val(),
            state : 1, // state�� 1
            value : message.value // �޽��� ����
          }
          // �޽��� ������
          webSocket.send(JSON.stringify(key));
          // �ؽ�Ʈ �ڽ� �ʱ�ȭ
          message.value = "";
    	  
      });
      
     // �ؽ�Ʈ �ڽ��� Ű�� ������ enter�Լ� ����
     // $("#textMessage").on("keydown", enter);
   
      
	});
  </script>