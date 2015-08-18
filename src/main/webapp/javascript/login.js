/*
 * Functions for the login page.
 */

function login(ref) {
    if (!$('#j_username').val().length) {
        alert('Please provide a user name to sign in.');
        return false;
    }
    if (!$('#j_password').val().length) {
        alert('Please provide a password to sign in.');
        return false;
    }
    return true;
}