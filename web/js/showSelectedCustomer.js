function showSelectedCustomer(dropdown) {
    var selectedCustomer = dropdown.options[dropdown.selectedIndex].value;
    var currentCustomer = document.getElementById('currentCustomer');
    currentCustomer.firstChild.nodeValue = selectedCustomer;
}