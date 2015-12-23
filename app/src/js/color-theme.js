import Colors from 'material-ui/lib/styles/colors';
import ColorManipulator from 'material-ui/lib/utils/color-manipulator';
import Spacing from 'material-ui/lib/styles/spacing';

export default {
  spacing: Spacing,
  fontFamily: 'Roboto, sans-serif',
  palette: {
    primary1Color: Colors.deepPurple900,
    primary2Color: Colors.deepPurple800,
    primary3Color: Colors.lightBlack,
    accent1Color: Colors.deepPurple700,
    accent2Color: Colors.deepPurple300,
    accent3Color: Colors.deepPurple200,
    textColor: Colors.darkBlack,
    alternateTextColor: Colors.white,
    canvasColor: Colors.white,
    borderColor: Colors.grey300,
    disabledColor: ColorManipulator.fade(Colors.darkBlack, 0.3),
  },
};