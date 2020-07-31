export function formatGender(value: boolean | null) {
  if (value == null) {
    return 'null';
  }
  if (value === true) {
    return 'Nam';
  } else {
    return 'Nữ';
  }
}