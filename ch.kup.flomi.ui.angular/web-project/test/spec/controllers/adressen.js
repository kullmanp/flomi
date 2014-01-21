'use strict';

describe('Controller: AdressenCtrl', function () {

  // load the controller's module
  beforeEach(module('webProjectApp'));

  var AdressenCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AdressenCtrl = $controller('AdressenCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
