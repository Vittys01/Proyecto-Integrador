export const getDateFormatted = (date: Date) => {
  return date
    .toLocaleDateString()
    .split('/')
    .map((el, i, dates) => {
      if (el.length === 1) el = '0' + el;
      return el;
    })
    .join('/');
};
