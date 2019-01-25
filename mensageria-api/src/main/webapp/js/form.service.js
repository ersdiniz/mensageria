(function() {
	'use strict';

	angular.module('app').factory('FormService', FormService);

	FormService.$inject = ['$injector'];

	function FormService($injector) {

		var Restangular = $injector.get('Restangular');

		var metodosPublicos = {};

		metodosPublicos.sendToKafka = _sendToKafka;
		metodosPublicos.getFromKafka = _getFromKafka;
		metodosPublicos.getWebSocket = _getWebSocket;

		function _sendToKafka(message) {
			return Restangular.all('kafka/v2/send').post(message);
		}

		function _getFromKafka(message) {
			return Restangular.one('kafka/v2/consume').get();
		}

		function _getWebSocket(host) {
			return new WebSocket("ws://localhost:10004/" + host);
		}

		return metodosPublicos;
	}
})();