import jwtDecode from 'jwt-decode';

export function getDecodedTokenFromCookie(cookieName) {
    const cookieValue = document.cookie.split('; ').find(row => row.startsWith(`${cookieName}=`))?.split('=')[1];

    if (!cookieValue) {
        return null;
    }
    try {
        const decoded = jwtDecode(cookieValue);
        return decoded;
    } catch (e) {
        console.error(e);
        return null;
    }
}

export function isEmailValid(email) {
    var regex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;
    return regex.test(email);
  }
  