export const getSingularCategory = (category: string) =>
  category.split(' ')[0].slice(0, -1) +
  ' ' +
  category.split(' ').slice(1).join(' ');
