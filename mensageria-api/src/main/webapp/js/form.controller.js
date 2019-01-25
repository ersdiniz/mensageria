(function() {
	'use strict';

    angular.module('app').controller('FormCtrl', FormCtrl);

    FormCtrl.$inject = ['$injector'];

    function FormCtrl($injector) {
        var vm = this;

        var wsActiveMQ,
            wsKafka;

        var FormService = $injector.get('FormService');

        var propriedadesPublicas = {
            sendToActiveMQ: _sendToActiveMQ,
            sendToKafkaV1: _sendToKafkaV1,
            sendToKafkaV2: _sendToKafkaV2,
            getFromKafkaV2: _getFromKafkaV2
        };

        _.extend(vm, propriedadesPublicas);

        init();

        function init() {
            wsActiveMQ = FormService.getWebSocket("activemq");
            wsKafka = FormService.getWebSocket("kafka");

            wsActiveMQ.onmessage = onMessageActiveMQ;
            wsKafka.onmessage = onMessageKafka;
        }

        function onMessageActiveMQ(response) {
            document.getElementById('activemqResposta').innerHTML = response.data;
        }

        function onMessageKafka(response) {
            document.getElementById('kafkaV1Resposta').innerHTML = response.data;
        }

        function _sendToActiveMQ(param) {
            wsActiveMQ.send(param.message);
        }

        function _sendToKafkaV1(param) {
            wsKafka.send(param.message);
        }

        function _sendToKafkaV2(param) {
            return FormService.sendToKafka({ mensagem: param.message });
        }

        function _getFromKafkaV2() {
            return FormService.getFromKafka().then(function(response) {
                vm.kafkaV2Resposta = response.mensagem;
            });
        }
    };
})();